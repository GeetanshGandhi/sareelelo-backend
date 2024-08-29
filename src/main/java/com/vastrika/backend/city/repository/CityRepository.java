package com.vastrika.backend.city.repository;

import com.vastrika.backend.city.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, String> {
    City findByCityName(String cityName);
}
