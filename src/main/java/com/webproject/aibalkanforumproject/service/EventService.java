package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Event;
import org.apache.tomcat.jni.Local;

import java.time.LocalDateTime;
import java.util.List;


public interface EventService {
    Event create(LocalDateTime start,LocalDateTime end,String text);
    Event changeDate(Long id,LocalDateTime start,LocalDateTime end);
    Event putColor(Long id,String color);
    List<Event> findBetween(LocalDateTime start,LocalDateTime end);
}
