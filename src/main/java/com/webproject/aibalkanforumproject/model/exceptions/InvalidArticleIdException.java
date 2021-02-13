package com.webproject.aibalkanforumproject.model.exceptions;

public class InvalidArticleIdException extends RuntimeException{
    public InvalidArticleIdException(Long id){
        super(String.format("Article with id %d do not exist",id));
    }
}
