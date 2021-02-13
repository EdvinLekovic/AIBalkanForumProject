package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.Favourite;
import com.webproject.aibalkanforumproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
//Made by Edvin Lekovic
@Repository
public interface FavouriteRepository extends JpaRepository<Favourite,Long> {
    Favourite findFavouriteByUser(User user);
}
