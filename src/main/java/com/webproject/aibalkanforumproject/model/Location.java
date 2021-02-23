package com.webproject.aibalkanforumproject.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String city;

    String country;

    public Location() {
    }

    public Location(String city, String country) {
        this.city = city;
        this.country = country;
    }

    public String getCityAndCountry(){
        return String.format("%s, %s",city,country);
    }
}
