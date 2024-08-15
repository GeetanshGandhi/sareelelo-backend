package com.vastrika.backend.customer.controller;

import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.customer.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("/loginCustomer")
    public String loginCustomer(@RequestBody Customer customer){
        return customerService.loginCustomer(customer);
    }

    @PostMapping("/registerCustomer")
    public String registerCustomer(@RequestBody Customer customer){
        return customerService.registerCustomer(customer);
    }
}

