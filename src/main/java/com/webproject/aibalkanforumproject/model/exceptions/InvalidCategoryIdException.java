package com.webproject.aibalkanforumproject.model.exceptions;

public class InvalidCategoryIdException extends RuntimeException {
    public InvalidCategoryIdException(Long categoryId) {
        super(String.format("Category with id %d do not exist",categoryId));
    }
}
