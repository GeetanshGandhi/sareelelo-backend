package com.vastrika.backend.business.service;

import com.vastrika.backend.business.model.Business;
import com.vastrika.backend.business.repository.BusinessRepository;
import com.vastrika.backend.category.model.Category;
import com.vastrika.backend.city.model.City;
import com.vastrika.backend.city.repository.CityRepository;
import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.customer.service.PasswordValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@SuppressWarnings("OptionalGetWithoutIsPresent")
@Service
public class BusinessService {

    @Autowired
    BusinessRepository businessRepository;

    @Autowired
    CityRepository cityRepository;
    public String registerBusiness(Business business, String cityName, String state, String categoryName){
        Optional<Business> isExist = businessRepository.findById(business.getOwnerEmail());
        if(isExist.isPresent()){
            return "Exists";
        }

        City city = cityRepository.findByCityNameAndState(cityName, state);
        business.setCity(city);
        business.setCategory(new Category(categoryName));
        String mob = business.getContactNo();
        if(mob.length()!=10) return "Invalid Mobile";
        for(int i = 0; i<10; i++){
            if (!Character.isDigit(mob.charAt(i))) return "Invalid Mobile";
        }
        if(!PasswordValidator.isPasswordValid(business.getPassword())) return "Invalid Password";
        business.setApproval("Unchecked");
        businessRepository.save(business);
        return "Wait for verification";
    }

    public String loginBusiness(Business business){
        Optional<Business> out = businessRepository.findById(business.getOwnerEmail());
        if (out.isPresent()){
            Business c = out.get();
            if(Objects.equals(c.getPassword(),business.getPassword()))
                return out.get().toString();
            return "Invalid";
        }
        return "Not Found";
    }

    public String changeApproval(Business business){
        Business old = businessRepository.findById(business.getOwnerEmail()).get();
        old.setApproval("Approved");
        return businessRepository.save(old).toString();
    }

    public List<Business> getUncheckedApprovals(){
        return businessRepository.findAllByApproval("Unchecked");
    }

    public List<Business> findBusinessByCity(String pinCode){
        return businessRepository.findAllByCity(cityRepository.findById(pinCode).get());
    }

    public Business getByEmail(String ownerEmail){
        return businessRepository.findById(ownerEmail).get();
    }
}
