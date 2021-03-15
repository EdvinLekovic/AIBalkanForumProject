package com.webproject.aibalkanforumproject.service.impl;

import com.webproject.aibalkanforumproject.model.enumerations.Provider;
import com.webproject.aibalkanforumproject.model.enumerations.Role;
import com.webproject.aibalkanforumproject.model.User;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidPasswordException;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidUsernameException;
import com.webproject.aibalkanforumproject.model.exceptions.PasswordsDoNotMatchException;
import com.webproject.aibalkanforumproject.model.exceptions.UsernameAlreadyExistsException;
import com.webproject.aibalkanforumproject.repository.UserRepository;
import com.webproject.aibalkanforumproject.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String name, String lastname, String password, String repeatPassword, Role role) {

        if (username==null || username.isEmpty())
            throw new InvalidUsernameException();

        if (password==null || password.isEmpty())
            throw new InvalidPasswordException();

        if(this.userRepository.findById(username).isPresent()){
            throw new UsernameAlreadyExistsException(username);
        }

        if(!password.equals(repeatPassword)){
            throw new PasswordsDoNotMatchException();
        }

        return userRepository.save(new User(username,name,lastname,passwordEncoder.encode(password), role,Provider.LOCAL));
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return this.userRepository.findById(s).orElseThrow(()->new UsernameNotFoundException(s));
    }

    public Optional<User> findByUserName(String username){
        return this.userRepository.findById(username);
    }

    @Override
    public User registerWithGoogle(String username, String name) {
        User user = getUser(username, name, Provider.GOOGLE);
        return this.userRepository.save(user);
    }

    @Override
    public User registerWithFacebook(String username, String name) {
        User user = getUser(username, name, Provider.FACEBOOK);
        return this.userRepository.save(user);
    }

    private User getUser(String username, String name, Provider provider) {
        if (username == null || username.isEmpty())
            throw new InvalidUsernameException();

        if (this.userRepository.findById(username).isPresent()) {
            throw new UsernameAlreadyExistsException(username);
        }
        User user = new User(username, name);
        user.setRole(Role.ROLE_USER);
        user.setPassword(passwordEncoder.encode("123"));
        user.setProvider(provider);
        return user;
    }
}
