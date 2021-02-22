package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Role;
import com.webproject.aibalkanforumproject.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

//Made by Edvin Lekovic
public interface UserService extends UserDetailsService {
    User register(String username,String name,String lastname,String password,String repeatPassword, Role role);
}
