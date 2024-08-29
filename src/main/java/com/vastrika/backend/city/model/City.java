package com.vastrika.backend.city.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class City {
    @Id
    private String cityId;

    private String cityName;
    private String state;

    public City(){}

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
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
                "\"cityId\":\"" + cityId + '\"'+
                ", \"cityName\":\"" + cityName + '\"' +
                ", \"state\":\"" + state + '\"' +
                '}';
    }
}
