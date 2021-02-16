package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.User;
//Made by Edvin Lekovic
public interface UserService{
    User register(String username,String name,String lastname,String password,String repeatPassword);
}
