package com.webproject.aibalkanforumproject.service.impl;


import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.repository.CategoryRepository;
import com.webproject.aibalkanforumproject.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> listCategories() {
        return this.categoryRepository.findAll();
    }

    @Override
    public List<Category> findCategoriesByText(String text) {
        return this.categoryRepository.findAllByNameLike(text);
    }
}
