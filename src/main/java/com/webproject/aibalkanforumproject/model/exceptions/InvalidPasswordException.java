package com.webproject.aibalkanforumproject.model.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(){
        super("The passwords do not match");
    }
}
