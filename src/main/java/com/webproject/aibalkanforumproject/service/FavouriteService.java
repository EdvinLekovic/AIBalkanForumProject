package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.Favourite;

import java.util.List;
import java.util.Optional;

public interface FavouriteService {
    List<Article> listAllArticlesInFavourite(Long faveId);
    Favourite getFavourite(String username);
    Favourite addArticleToFavourites(String username,Long articleId);
    Optional<Article> delete(Long id, String username);
}
