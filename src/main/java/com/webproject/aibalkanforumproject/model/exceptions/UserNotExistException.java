package com.webproject.aibalkanforumproject.model.exceptions;

public class UserNotExistException extends RuntimeException {

    public UserNotExistException(String username) {

        super(String.format("User with username %s does not exist", username));
    }
}
