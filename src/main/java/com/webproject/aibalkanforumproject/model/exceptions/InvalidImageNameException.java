package com.webproject.aibalkanforumproject.model.exceptions;

public class InvalidImageNameException extends RuntimeException {
    public InvalidImageNameException(String name) {
        super(String.format("Image with name %s do not exist",name));
    }
}
