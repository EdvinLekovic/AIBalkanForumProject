package com.webproject.aibalkanforumproject.service.impl;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.Favourite;
import com.webproject.aibalkanforumproject.model.User;
import com.webproject.aibalkanforumproject.model.exceptions.FavouritesNotFound;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidArticleIdException;
import com.webproject.aibalkanforumproject.model.exceptions.UserNotExistException;
import com.webproject.aibalkanforumproject.repository.ArticleRepository;
import com.webproject.aibalkanforumproject.repository.FavouriteRepository;
import com.webproject.aibalkanforumproject.repository.UserRepository;
import com.webproject.aibalkanforumproject.service.FavouriteService;
import org.springframework.stereotype.Service;

import java.util.List;
//Made by Edvin Lekovic
@Service
public class FavouriteServiceImpl implements FavouriteService {

    private final FavouriteRepository favouriteRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public FavouriteServiceImpl(FavouriteRepository favouriteRepository, UserRepository userRepository, ArticleRepository articleRepository) {
        this.favouriteRepository = favouriteRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> findAll(Long id) {
        return favouriteRepository.findById(id).orElseThrow(()->new FavouritesNotFound(id)).getArticles();
    }

    @Override
    public List<Article> findByUser(String username) {
        User user = userRepository.findById(username).orElseThrow(()-> new UserNotExistException(username));
        return favouriteRepository.findFavouriteByUser(user).getArticles();
    }

    @Override
    public Favourite addArticle(String username, Long articleId) {
       Article article = articleRepository.findById(articleId).orElseThrow(()->new InvalidArticleIdException(articleId));
       User user = userRepository.findById(username).orElseThrow(()-> new UserNotExistException(username));
       Favourite favourite = favouriteRepository.findFavouriteByUser(user);
       favourite.getArticles().add(article);
       return favourite;
    }


}
