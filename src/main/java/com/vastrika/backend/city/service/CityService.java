package com.vastrika.backend.city.service;

import com.vastrika.backend.city.model.City;
import com.vastrika.backend.city.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public String addCity(City city){
        if(city.getPinCode().length()!=6) return "Invalid pincode";
        for(int i = 0; i<6; i++){
            char c = city.getPinCode().charAt(i);
            if(c<48 || c>57) return "Invalid pincode";
        }
        for(int i = 0; i<city.getCityName().length(); i++){
            if(!Character.isLetter(city.getCityName().charAt(i))) return "Invalid Name";
        }
        return cityRepository.save(city).toString();
    }

    public City getCityByName(String sname){
        City found = cityRepository.findByCityName(sname);
        if(found==null) return new City();
        return found;
    }

    public City getCityById(String cityId){
        return cityRepository.findById(cityId).get();
    }

    public List<City> getCitiesByState(String state){
        List<City> found = cityRepository.findAllByState(state);
        return found;
    }
}
