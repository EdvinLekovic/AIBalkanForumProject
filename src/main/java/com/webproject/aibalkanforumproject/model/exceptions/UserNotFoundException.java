package com.webproject.aibalkanforumproject.model.exceptions;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String id) {
        super(String.format("User with id(username) %s does not exist", id));
    }

}
