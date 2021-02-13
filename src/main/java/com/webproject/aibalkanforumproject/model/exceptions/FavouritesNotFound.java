package com.webproject.aibalkanforumproject.model.exceptions;

import com.webproject.aibalkanforumproject.model.Favourite;

public class FavouritesNotFound extends RuntimeException{
    public FavouritesNotFound(Long id){
        super(String.format("Favourite articles with id %d do not exists",id));
    }
}
