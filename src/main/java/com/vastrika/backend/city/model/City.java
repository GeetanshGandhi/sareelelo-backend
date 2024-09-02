package com.vastrika.backend.city.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.annotations.Type;

@Entity
public class City {
    @Id
    @Column(columnDefinition = "varchar(6) DEFAULT '000000'")
    private String pinCode;

    private String cityName;
    private String state;

    public City(){}

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "{" +
                "\"pinCode\":\"" + pinCode + '\"'+
                ", \"cityName\":\"" + cityName + '\"' +
                ", \"state\":\"" + state + '\"' +
                '}';
    }
}
