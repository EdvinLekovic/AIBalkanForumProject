package com.webproject.aibalkanforumproject.service.impl;


import com.webproject.aibalkanforumproject.model.exceptions.InvalidArticleIdException;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidTitleException;
import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.User;
import com.webproject.aibalkanforumproject.model.exceptions.UserNotExistException;
import com.webproject.aibalkanforumproject.repository.ArticleRepository;
import com.webproject.aibalkanforumproject.repository.UserRepository;
import com.webproject.aibalkanforumproject.service.ArticleService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
//Made by Edvin Lekovic
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> findByUser(String username) {
        User user = userRepository.findById(username).orElseThrow(()-> new UserNotExistException(username));
        return articleRepository.findArticlesByUser(user);
    }

    @Override
    public List<Article> findByCategory(Category category) {
        return articleRepository.findArticlesByCategory(category);
    }

    @Override
    public List<Article> findByTitle(String title) {
        return articleRepository.findArticlesByTitle(title);
    }

    @Override
    public Article create(String title, String description, Category category, User user) {

        if(articleRepository.findArticlesByTitle(title).stream().anyMatch(a->a.getTitle().equals(title))){
            throw new InvalidTitleException();
        }
        return articleRepository.save(new Article(title,description,category,user));
    }

    @Override
    public Article edit(Long id, String title, String description, Category category) {
        Article article = articleRepository.findById(id).orElseThrow(()->new InvalidArticleIdException(id));
        if(articleRepository.findArticlesByTitle(title).stream().anyMatch(a->a.getTitle().equals(title))){
            throw new InvalidTitleException();
        }
        article.setTitle(title);
        article.setDescription(description);
        article.setCategory(category);
        article.setLastChangeDate(LocalDateTime.now());
        return articleRepository.save(article);
    }

    public Article delete(Long id){
        Article article = articleRepository.findById(id).orElseThrow(()->new InvalidArticleIdException(id));
        articleRepository.delete(article);
        return article;
    }
}
