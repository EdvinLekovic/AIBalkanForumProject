package com.webproject.aibalkanforumproject.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    private Question question;

    @ManyToOne
    private User user;

    public Answer() {
    }

    public Answer(String description, Question question) {
        this.description = description;
        this.question = question;
    }
}
