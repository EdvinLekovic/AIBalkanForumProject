package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Category;

import java.util.List;

//Made by Filip Stavrov

public interface CategoryService {

    List<Category> listCategories();
    List<Category> findCategoriesByText(String text);

}
