package com.webproject.aibalkanforumproject.model.exceptions;

public class InvalidImageIdException extends RuntimeException {
    public InvalidImageIdException(Long id){
        super(String.format("Image with id %d do not exist",id));
    }
}
