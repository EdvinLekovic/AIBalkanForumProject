package com.webproject.aibalkanforumproject.service.impl;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.Favourite;
import com.webproject.aibalkanforumproject.model.User;
import com.webproject.aibalkanforumproject.model.exceptions.*;
import com.webproject.aibalkanforumproject.repository.ArticleRepository;
import com.webproject.aibalkanforumproject.repository.FavouriteRepository;
import com.webproject.aibalkanforumproject.repository.UserRepository;
import com.webproject.aibalkanforumproject.service.FavouriteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


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
    public List<Article> listAllArticlesInFavourite(Long faveId){
        if(!this.favouriteRepository.findById(faveId).isPresent()){
            throw new FavouritesNotFound(faveId);
        }
        return this.favouriteRepository.findById(faveId).get().getArticles();
    }

    @Override
    public Favourite getFavourite(String username) {

        User user = this.userRepository.findById(username).
                orElseThrow(() -> new UserNotFoundException(username));
        return this.favouriteRepository.findFavouriteByUser(user)
                .orElseGet(() -> {
                    Favourite favourite = new Favourite(user);
                    return this.favouriteRepository.save(favourite);
                });
    }

    @Override
    public Favourite addArticleToFavourites(String username, Long articleId) {
        Favourite favourite = this.getFavourite(username);
        Article article = this.articleRepository.findById(articleId).
                orElseThrow(() -> new InvalidArticleIdException(articleId));
        if(favourite.getArticles().stream().anyMatch(a-> a.getId().equals(articleId)))
            throw new ArticleAlreadyInFavourites(articleId, username);
        favourite.getArticles().add(article);

        return this.favouriteRepository.save(favourite);
    }

    @Override
    public Optional<Article> delete(Long articleId,String username) {
        Favourite favourite = this.getFavourite(username);
        Optional<Article> article = favourite.getArticles().stream().filter(a->a.getId().equals(articleId)).findAny();
        article.ifPresent(value -> favourite.getArticles().remove(value));
        this.favouriteRepository.save(favourite);
        return article;
    }


}
