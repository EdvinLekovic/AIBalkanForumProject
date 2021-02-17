package com.webproject.aibalkanforumproject.model;

import org.springframework.security.core.GrantedAuthority;


//Made by Filip Stavrov

public enum Role implements GrantedAuthority {

    ROLE_USER, ROLE_ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
