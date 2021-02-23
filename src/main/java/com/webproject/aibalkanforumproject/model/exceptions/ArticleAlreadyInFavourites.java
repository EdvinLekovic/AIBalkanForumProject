package com.webproject.aibalkanforumproject.model.exceptions;

public class ArticleAlreadyInFavourites extends RuntimeException {

    public ArticleAlreadyInFavourites(Long articleId, String username) {
        super(String.format("Article with id %d already exists in favourites for user with username %s", articleId, username));
    }
}
