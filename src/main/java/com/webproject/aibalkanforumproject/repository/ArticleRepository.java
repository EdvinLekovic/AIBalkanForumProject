package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
//Made by Edvin Lekovic
public interface ArticleRepository extends JpaRepository<Article,Long> {
    List<Article> findArticlesByUser(User user);
    List<Article> findArticlesByCategory(Category category);
    List<Article> findArticlesByTitle(String title);
}
