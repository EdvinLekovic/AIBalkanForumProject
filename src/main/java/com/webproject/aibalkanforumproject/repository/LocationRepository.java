package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
    List<Location> findLocationsByCity(String city);

    List<Location> findLocationsByCountry(String country);

    List<Location> findLocationsByCityAndCountry(String city, String country);
}
