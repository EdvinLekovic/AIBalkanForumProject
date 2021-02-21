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

    @Column(length = 1000)
    String description;

    String urlImage;

    @ManyToOne
    Category category;

    LocalDateTime dateCreated;

    LocalDateTime lastChangeDate;

    @ManyToOne
    User user;

    public Article() {
    }

    public Article(String title, String description, String urlImage, Category category, User user) {
        this.title = title;
        this.description = description;
        this.urlImage = urlImage;
        this.category = category;
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.lastChangeDate = LocalDateTime.now();
    }
}
