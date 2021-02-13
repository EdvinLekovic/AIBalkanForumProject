package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.User;

import java.util.List;
//Made by Edvin Lekovic
public interface ArticleService {
    List<Article> findAll();
    List<Article> findByUser(String username);
    List<Article> findByCategory(Category category);
    List<Article> findByTitle(String title);
    Article create(String title, String description, Category category, User user);
    Article edit(Long id,String title,String description,Category category);
    Article delete(Long id);
}
