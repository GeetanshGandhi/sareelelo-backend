package com.vastrika.backend.business.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vastrika.backend.business.model.Business;
import com.vastrika.backend.business.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/business")
@CrossOrigin
public class BusinessController {

    @Autowired
    BusinessService businessService;

    @PostMapping("/register")
    public String registerBusiness(@RequestParam("businessDet") String businessString,
                                   @RequestParam("city") String cityName,
                                   @RequestParam("state") String state,
                                   @RequestParam("categoryName") String categoryName){
        ObjectMapper mapper = new ObjectMapper();
        Business business = null;
        try {
            business = mapper.readValue(businessString, Business.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return businessService.registerBusiness(business,cityName, state, categoryName);
    }

    @PostMapping("/login")
    public String businessLogin(@RequestBody Business business){
        return businessService.loginBusiness(business);
    }

    @PostMapping("/changeApproval")
    public String changeApprovalStat(@RequestBody Business business){
        return businessService.changeApproval(business);
    }

    @GetMapping("/getUnchecked")
    public List<Business> getUnchecked(){
        return businessService.getUncheckedApprovals();
    }
}
