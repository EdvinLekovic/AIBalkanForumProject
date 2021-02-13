package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Made by Filip Stavrov
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByNameLike(String name);
}
