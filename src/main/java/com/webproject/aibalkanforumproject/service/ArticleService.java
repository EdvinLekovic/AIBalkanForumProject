package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.Image;
import com.webproject.aibalkanforumproject.model.User;

import java.util.List;
//Made by Edvin Lekovic
public interface ArticleService {
    List<Article> findAll();
    List<Article> findByUser(String username);
    List<Article> findByCategory(Long categoryId);
    List<Article> findByTitle(String title);
    Article findById(Long id);
    Article create(String title, String description, Long categoryId,Image image, String userId);
    Article edit(Long id,String title,String description,Long categoryId,Image image);
    Article delete(Long id);
}
