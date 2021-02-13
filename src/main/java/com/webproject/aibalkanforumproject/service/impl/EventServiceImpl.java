package com.webproject.aibalkanforumproject.service.impl;

import com.webproject.aibalkanforumproject.model.Event;
import com.webproject.aibalkanforumproject.model.exceptions.EventNotFoundException;
import com.webproject.aibalkanforumproject.repository.EventRepository;
import com.webproject.aibalkanforumproject.service.EventService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

//Made by Filip Stavrov

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public Event create(String title, String addressOrUrl, String hostedBy) {
        if(title.isEmpty() || addressOrUrl.isEmpty() || hostedBy.isEmpty()){
            throw new IllegalArgumentException();
        }
        return new Event(title, addressOrUrl, hostedBy);
    }

    @Override
    public Event delete(Long id) {
        Event event = this.eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
        this.eventRepository.deleteById(id);
        return event;
    }

    @Override
    public List<Event> findEventsByHost(String hostedBy) {
        return this.eventRepository.findAllByHostedBy(hostedBy);
    }

    @Override
    public List<Event> findEventsByDate(LocalDateTime date) {
        return this.eventRepository.findAllByDate(date);
    }
}
