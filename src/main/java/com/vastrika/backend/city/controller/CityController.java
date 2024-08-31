package com.vastrika.backend.city.controller;

import com.vastrika.backend.city.model.City;
import com.vastrika.backend.city.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/city")
@CrossOrigin
public class CityController {

    @Autowired
    CityService cityService;

    @PostMapping("/add")
    public String addCity(@RequestBody City  city){
        return cityService.addCity(city);
    }

    @PostMapping("/getByName")
    public City getCityByCityName(@RequestBody String city){
        return cityService.getCityByName(city);
    }
    
    @PostMapping("/getById")
    public City getCityByCityId(@RequestBody String cityId){
        return cityService.getCityById(cityId);
    }

    @PostMapping("/getbyState")
    public List<City> getCitiesByState(@RequestBody String state){
        return cityService.getCitiesByState(state);
    }
}
