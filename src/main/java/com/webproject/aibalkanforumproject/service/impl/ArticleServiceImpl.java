package com.webproject.aibalkanforumproject.service.impl;


import com.webproject.aibalkanforumproject.model.exceptions.*;
import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.User;
import com.webproject.aibalkanforumproject.repository.ArticleRepository;
import com.webproject.aibalkanforumproject.repository.CategoryRepository;
import com.webproject.aibalkanforumproject.repository.UserRepository;
import com.webproject.aibalkanforumproject.service.ArticleService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
//Made by Edvin Lekovic
@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    public ArticleServiceImpl(ArticleRepository articleRepository, UserRepository userRepository, CategoryRepository categoryRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public List<Article> findByUser(String username) {
        User user = this.userRepository.findById(username).orElseThrow(() -> new UserNotExistException(username));
        return articleRepository.findArticlesByUser(user);
    }

    @Override
    public List<Article> findByCategory(Long categoryId) {
        Category category = this.categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return articleRepository.findArticlesByCategory(category);
    }

    @Override
    public List<Article> findByTitle(String title) {
        return articleRepository.findArticlesByTitleLike(title);
    }

    @Override
    public Article create(String title, String description, String urlImage, Long categoryId, String userId) {

        Category category = this.categoryRepository.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException(categoryId));

        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException(userId));

        if(articleRepository.findArticlesByTitle(title).stream().anyMatch(a->a.getTitle().equals(title))){
            throw new InvalidTitleException();
        }
        return articleRepository.save(new Article(title, description, urlImage, category, user));
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
