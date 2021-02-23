package com.webproject.aibalkanforumproject.config;

import com.webproject.aibalkanforumproject.model.Role;
import com.webproject.aibalkanforumproject.model.User;
import com.webproject.aibalkanforumproject.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Optional;

@Component
public class DataInitializer {

    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void initAdmins() {
        Optional<User> user1 = userService.findByUserName("edvin12");
        Optional<User> user2 = userService.findByUserName("filip07");
        if (user1.isEmpty() && user2.isEmpty()) {
            this.userService.register("edvin12", "Edvin", "Lekovic", "edvin", "edvin", Role.ROLE_ADMIN);
            this.userService.register("filip07", "Filip", "Stavrov", "filip", "filip", Role.ROLE_ADMIN);
        }
    }
}
