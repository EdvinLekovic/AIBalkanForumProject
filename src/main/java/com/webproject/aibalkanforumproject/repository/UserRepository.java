package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
//Made by Edvin Lekovic
public interface UserRepository extends JpaRepository<User,String> {

}
