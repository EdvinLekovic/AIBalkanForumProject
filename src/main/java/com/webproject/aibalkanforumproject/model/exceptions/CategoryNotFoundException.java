package com.webproject.aibalkanforumproject.model.exceptions;

import com.webproject.aibalkanforumproject.model.Category;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Long id) {
        super(String.format("Category with id %d does not exist", id));
    }

}
