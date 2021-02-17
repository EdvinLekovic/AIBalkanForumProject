package com.webproject.aibalkanforumproject.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
//Made by Filip Stavrov
@Data
@Entity
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String addressOrUrl;

    LocalDateTime date;

    String host;

    public Event() {
    }

    public Event(String title, String addressOrUrl,String host) {
        this.title = title;
        this.addressOrUrl = addressOrUrl;
        this.date = LocalDateTime.now();
        this.host = host;
    }
}
