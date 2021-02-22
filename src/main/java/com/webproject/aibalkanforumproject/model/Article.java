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
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    @ManyToOne
    private Image image;

    @ManyToOne
    private Category category;

    private LocalDateTime dateCreated;

    private LocalDateTime lastChangeDate;



    @ManyToOne
    User user;

    public Article() {
    }

    public Article(String title, String description, Image image, Category category, User user) {
        this.title = title;
        this.description = description;
        this.image = image;
        this.category = category;
        this.user = user;
        this.dateCreated = LocalDateTime.now();
        this.lastChangeDate = LocalDateTime.now();
    }
}
