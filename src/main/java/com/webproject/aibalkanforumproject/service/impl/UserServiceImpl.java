package com.webproject.aibalkanforumproject.service.impl;

import com.webproject.aibalkanforumproject.model.exceptions.InvalidPasswordException;
import com.webproject.aibalkanforumproject.model.User;
import com.webproject.aibalkanforumproject.model.exceptions.UserNotExistException;
import com.webproject.aibalkanforumproject.repository.UserRepository;
import com.webproject.aibalkanforumproject.service.UserService;
import org.springframework.stereotype.Service;
//Made by Edvin Lekovic
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    //private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User register(String username, String name, String lastname, String password, String repeatPassword) {

        if(userRepository.findById(username).isPresent()){
            throw new UserNotExistException(username);
        }

        if(!password.equals(repeatPassword)){
            throw new InvalidPasswordException();
        }

        return userRepository.save(new User(username,name,lastname,password));
    }

}
