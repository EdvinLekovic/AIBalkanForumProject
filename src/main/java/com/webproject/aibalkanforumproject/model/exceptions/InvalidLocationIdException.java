package com.webproject.aibalkanforumproject.model.exceptions;

public class InvalidLocationIdException extends RuntimeException {
    public InvalidLocationIdException(Long locationId) {
        super(String.format("Location with id %d do not exist",locationId));
    }
}
