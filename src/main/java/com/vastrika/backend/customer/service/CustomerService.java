package com.vastrika.backend.customer.service;

import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    public String loginCustomer(Customer customer){
        Optional<Customer> out = customerRepository.findById(customer.getCustomerEmail());
        if (out.isPresent()){
            Customer c = out.get();
            if(Objects.equals(c.getPassword(),customer.getPassword()))
                return out.get().toString();
            return "Invalid";
        }
        return "Not Found";
    }

    public String registerCustomer(Customer customer){
        if(userExists(customer.getCustomerEmail())) return "Exist";
        if(!PasswordValidator.isPasswordValid(customer.getPassword())) return "Invalid Password";
        customerRepository.save(customer);
        return "Success";
    }


    //check if user exists
    boolean userExists(String email){
        return customerRepository.findById(email).isPresent();
    }
}

