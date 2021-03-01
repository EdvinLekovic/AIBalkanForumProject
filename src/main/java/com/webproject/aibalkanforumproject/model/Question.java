package com.webproject.aibalkanforumproject.model;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String title;

    @Column(length = 2000)
    private String description;

    @ManyToOne
    private User user;

    private LocalDateTime dateCreated;


    public Question() {
    }

    public Question(String title, String description,User user) {
        this.title = title;
        this.description = description;
        this.user = user;
        this.dateCreated = LocalDateTime.now();
    }
}
