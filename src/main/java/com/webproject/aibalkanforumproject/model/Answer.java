package com.webproject.aibalkanforumproject.model;


import lombok.Data;

import javax.persistence.*;
//Made by Filip Stavrov
@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String description;

    @ManyToOne
    Question question;

    @ManyToOne
    User user;

    public Answer() {
    }

    public Answer(String description, Question question) {
        this.description = description;
        this.question = question;
    }
}
