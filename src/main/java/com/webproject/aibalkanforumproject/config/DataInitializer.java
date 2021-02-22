package com.webproject.aibalkanforumproject.config;

import com.webproject.aibalkanforumproject.model.Role;
import com.webproject.aibalkanforumproject.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initAdmins() {
        UserDetails userDetails1 = userService.loadUserByUsername("edvin12");
        UserDetails userDetails2 = userService.loadUserByUsername("filip07");
        if (userDetails1 == null && userDetails2 == null) {
            this.userService.register("edvin12", "Edvin", "Lekovic", "edvin", "edvin", Role.ROLE_ADMIN);
            this.userService.register("filip07", "Filip", "Stavrov", "filip", "filip", Role.ROLE_ADMIN);
        }
    }
}
