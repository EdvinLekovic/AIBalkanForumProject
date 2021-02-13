package com.webproject.aibalkanforumproject.model.exceptions;

public class AnswerNotFoundException extends RuntimeException {

    public AnswerNotFoundException(Long id) {
        super(String.format("Answer with id %d does not exist", id));
    }
}
