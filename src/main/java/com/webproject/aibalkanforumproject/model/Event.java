package com.webproject.aibalkanforumproject.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity

public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String addressOrUrl;

    LocalDateTime date;

    String HostedBy;

    public Event() {
    }

    public Event(String title, String addressOrUrl, LocalDateTime date, String hostedBy) {
        this.title = title;
        this.addressOrUrl = addressOrUrl;
        this.date = date;
        HostedBy = hostedBy;
    }
}
