package com.webproject.aibalkanforumproject.model.exceptions;

public class InvalidJobIdException extends RuntimeException {
    public InvalidJobIdException(Long id){
        super(String.format("Job with id %d do not exist",id));
    }
}
