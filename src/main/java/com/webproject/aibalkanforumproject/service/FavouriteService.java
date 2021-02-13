package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.Favourite;

import java.util.List;
//Made by Edvin Lekovic
public interface FavouriteService {
    List<Article> findAll(Long id);
    List<Article> findByUser(String username);
    Favourite addArticle(String username,Long articleId);
}
