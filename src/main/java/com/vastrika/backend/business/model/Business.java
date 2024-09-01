package com.vastrika.backend.business.model;

import com.vastrika.backend.category.model.Category;
import com.vastrika.backend.city.model.City;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Business {

    @Id
    private String ownerEmail;
    private String gstin;
    private String businessName;
    private String businessOwnerName;
    private String contactNo;
    private String address;
    private String password;
    private String approval;

    @ManyToOne
    @JoinColumn(name="pinCode")
    private City city;

    @ManyToOne
    @JoinColumn(name = "categoryName")
    private Category category;

    public Business(){}

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    public String getGstin() {
        return gstin;
    }

    public void setGstin(String gstin) {
        this.gstin = gstin;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getBusinessOwnerName() {
        return businessOwnerName;
    }

    public void setBusinessOwnerName(String businessOwnerName) {
        this.businessOwnerName = businessOwnerName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getApproval() {
        return approval;
    }

    public void setApproval(String approval) {
        this.approval = approval;
    }

    @Override
    public String toString() {
        return "{" +
                "\"ownerEmail\":\"" + ownerEmail + '\"' +
                ", \"gstin\":\"" + gstin + '\"' +
                ", \"businessName\":\"" + businessName + '\"' +
                ", \"businessOwnerName\":\"" + businessOwnerName + '\"' +
                ", \"contactNo\":\"" + contactNo + '\"' +
                ", \"address\":\"" + address + '\"' +
                ", \"password\":\"" + password + '\"' +
                ", \"city\":" + city.toString() +
                ", \"category\":" + category.toString() +
                ", \"approval\":\"" + approval + '\"' +
                '}';
    }
}
