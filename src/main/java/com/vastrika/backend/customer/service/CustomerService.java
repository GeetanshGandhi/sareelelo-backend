package com.vastrika.backend.customer.service;

import com.vastrika.backend.customer.model.Customer;
import com.vastrika.backend.customer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Pattern;

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
        System.out.println(customer.toString());
        if(userExists(customer.getCustomerEmail())) return "Exist";
        if(!isPasswordValid(customer.getPassword())) return "Invalid Password";
        customerRepository.save(customer);
        return "Success";
    }

    public static boolean isPasswordValid(String password) {
        if (password.length() < 8 || password.length()>16)
            return false;
        else {
            String uppercaseRegex = ".*[A-Z].*";
            String lowercaseRegex = ".*[a-z].*";
            String digitRegex = ".*\\d.*";
            String specialCharRegex = ".*[@#$%^&!/].*";

            boolean hasUppercase = Pattern.matches(uppercaseRegex, password);
            boolean hasLowercase = Pattern.matches(lowercaseRegex, password);
            boolean hasDigit = Pattern.matches(digitRegex, password);
            boolean hasSpecialChar = Pattern.matches(specialCharRegex, password);

            return hasUppercase && hasLowercase && hasDigit && hasSpecialChar;
        }
    }

    //check if user exists
    boolean userExists(String email){
        return customerRepository.findById(email).isPresent();
    }
}
