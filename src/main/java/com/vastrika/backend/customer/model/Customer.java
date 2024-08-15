package com.vastrika.backend.customer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Customer {

    @Id
    private String customerEmail;
    private String customerName;
    private String mobile;
    private String password;
    private String Address;

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String toString() {
        return "{" +
                "\"customerEmail\":\"" + customerEmail + '\"' +
                ", \"customerName\":\"" + customerName + '\"' +
                ", \"mobile\":\"" + mobile + '\"' +
                ", \"password\":\"" + password + '\"' +
                ", \"Address\":\"" + Address + '\"' +
                '}';
    }
}
