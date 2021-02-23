package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Location;

import java.util.List;

public interface LocationService {
    List<Location> findByCity(String city);
    List<Location> findByCountry(String country);
    List<Location> findByCityAndCountry(String city,String country);
    List<Location> findAll();
}
