package com.webproject.aibalkanforumproject.model;


import lombok.Data;

import javax.persistence.*;
import java.util.List;
//Made by Filip Stavrov
@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "question", fetch = FetchType.EAGER)
    private List<Answer> answers;

    public Question() {
    }

    public Question(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
