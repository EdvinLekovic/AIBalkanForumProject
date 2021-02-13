package com.webproject.aibalkanforumproject.model.exceptions;

public class QuestionNotFoundException extends RuntimeException {

    public QuestionNotFoundException(Long id) {
        super(String.format("Question with id %d does not exist", id));
    }
}
