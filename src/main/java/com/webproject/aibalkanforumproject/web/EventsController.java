package com.webproject.aibalkanforumproject.web;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.webproject.aibalkanforumproject.model.Event;
import com.webproject.aibalkanforumproject.repository.EventRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@RestController
public class EventsController {


    private final EventRepository eventRepository;

    public EventsController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @RequestMapping("/")
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.getModel().put("bodyContent","calendar.html");
        modelAndView.setViewName("master-template");
        return modelAndView;
    }

    @GetMapping("/api/events")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Iterable<Event> events(@RequestParam("startTime")
                           @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime startTime,
                           @RequestParam("endTime")
                           @DateTimeFormat(iso = ISO.DATE_TIME) LocalDateTime endTime) {
        return eventRepository.findBetween(startTime, endTime);
    }

    @PostMapping("/api/events/create")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    Event createEvent(@RequestBody EventCreateParams params) {

        Event e = new Event();
        e.setStartTime(params.start);
        e.setEndTime(params.end);
        e.setText(params.text);

        eventRepository.save(e);

        return e;
    }

    @PostMapping("/api/events/move")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event moveEvent(@RequestBody EventMoveParams params) {

        Event e = eventRepository.findById(params.id).get();

        e.setStartTime(params.start);
        e.setEndTime(params.end);

        eventRepository.save(e);

        return e;
    }

    @PostMapping("/api/events/setColor")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @Transactional
    Event setColor(@RequestBody SetColorParams params) {

        Event e = eventRepository.findById(params.id).get();
        e.setColor(params.color);
        eventRepository.save(e);

        return e;
    }

    public static class EventCreateParams {
        public LocalDateTime start;
        public LocalDateTime end;
        public String text;
        public Long resource;
    }

    public static class EventMoveParams {
        public Long id;
        public LocalDateTime start;
        public LocalDateTime end;
        public Long resource;
    }

    public static class SetColorParams {
        public Long id;
        public String color;
    }


}