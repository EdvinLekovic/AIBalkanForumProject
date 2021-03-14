package com.webproject.aibalkanforumproject.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table(name = "Ai_users")
public class User implements UserDetails {
    @Id
    private String username;

    private String name;

    private String lastname;

    private String password;

    @ManyToOne
    private Image image;

    private boolean isAccountNonExpired = true;

    private boolean isAccountNonLocked = true;

    private boolean isCredentialsNonExpired = true;

    private boolean isEnabled = true;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @OneToOne
    private Favourite favourite;


    public User() {
    }

    public User(String username, String name){
        this.username = username;
        this.name = name;
    }

    public User(String username,String name, String lastname, String password, Role role) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(role);
    }

    @Override
    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
