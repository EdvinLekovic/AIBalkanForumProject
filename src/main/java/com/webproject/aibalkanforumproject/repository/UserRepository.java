package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
//Made by Edvin Lekovic
@Repository
public interface UserRepository extends JpaRepository<User,String> {
}
