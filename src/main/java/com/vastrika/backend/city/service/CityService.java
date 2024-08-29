package com.vastrika.backend.city.service;

import com.vastrika.backend.city.model.City;
import com.vastrika.backend.city.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CityService {

    @Autowired
    CityRepository cityRepository;

    public String addCity(City city){
        if(city.getCityId().length()!=3) return "Invalid ID";
        for(int i = 0; i<3; i++){
            char c = city.getCityId().charAt(i);
            if(c<65 || c>90) return "Invalid ID";
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
}
