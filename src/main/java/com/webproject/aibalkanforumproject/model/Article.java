package com.webproject.aibalkanforumproject.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
//Made by Edvin Lekovic
@Data
@Entity
public class Article {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    Long id;

    String title;

    String description;

    @ManyToOne
    Category category;

    LocalDateTime dateCreated;

    LocalDateTime lastChangeDate;

    @ManyToOne
    User user;

    public Article() {
    }

    public Article(String title, String description, Category category, User user) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.lastChangeDate = LocalDateTime.now();
    }
}
