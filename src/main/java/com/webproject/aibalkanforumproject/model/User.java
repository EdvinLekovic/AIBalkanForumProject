package com.webproject.aibalkanforumproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
//Made by Edvin Lekovic
@Data
@Entity
@Table(name = "Ai_users")
public class User {

    @Id
    String username;

    String name;

    String lastname;

    String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Article> articles;

    @OneToOne
    Favourite favourite;


    public User() {
    }

    public User(String username,String name, String lastname, String password) {
        this.username = username;
        this.name = name;
        this.lastname = lastname;
        this.password = password;
    }
}
