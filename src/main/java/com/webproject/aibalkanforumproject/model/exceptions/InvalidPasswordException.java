package com.webproject.aibalkanforumproject.model.exceptions;

public class InvalidPasswordException extends RuntimeException{
    public InvalidPasswordException(){
        super("The password you entered is not valid.");
    }
}
