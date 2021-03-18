package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findArticlesByUser(User user);

    List<Article> findArticlesByCategory(Category category);

    List<Article> findArticlesByTitleLike(String title);

    List<Article> findArticlesByTitle(String title);
}
