package com.webproject.aibalkanforumproject.model.exceptions;

public class EventNotFoundException extends RuntimeException {

    public EventNotFoundException(Long id) {
        super(String.format("Event with id %d does not exist", id));
    }

}
