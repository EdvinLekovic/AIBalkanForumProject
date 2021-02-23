package com.webproject.aibalkanforumproject.service.impl;

import com.webproject.aibalkanforumproject.model.Location;
import com.webproject.aibalkanforumproject.repository.LocationRepository;
import com.webproject.aibalkanforumproject.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findByCity(String city) {
        return locationRepository.findLocationsByCity(city);
    }

    @Override
    public List<Location> findByCountry(String country) {
        return locationRepository.findLocationsByCountry(country);
    }

    @Override
    public List<Location> findByCityAndCountry(String city, String country) {
        return locationRepository.findLocationsByCityAndCountry(city,country);
    }

    @Override
    public List<Location> findAll() {
        return locationRepository.findAll();
    }


}
