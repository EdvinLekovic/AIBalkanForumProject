package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Event;

import java.time.LocalDateTime;
import java.util.List;


public interface EventService {

    Event create(String title, String addressOrUrl, String hostedBy);
    Event delete(Long id);
    List<Event> findEventsByHost(String host);
    List<Event> findEventsByDate(LocalDateTime date);
}
