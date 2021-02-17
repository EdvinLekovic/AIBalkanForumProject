package com.webproject.aibalkanforumproject.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException{

    public PasswordsDoNotMatchException() {
        super("The passwords do not match. Please try again.");
    }
}
