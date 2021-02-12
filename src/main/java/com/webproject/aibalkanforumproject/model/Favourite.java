package com.webproject.aibalkanforumproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;

@Data
@Entity
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    User user;

    @ManyToMany
    List<Article> articles;

    public Favourite() {
    }

    public Favourite(User user) {
        this.user = user;
        this.articles = new ArrayList<>();
    }
}
