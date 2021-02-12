package com.webproject.aibalkanforumproject.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String description;

    @ManyToOne
    Question question;

    public Answer() {
    }

    public Answer(String description, Question question) {
        this.description = description;
        this.question = question;
    }
}
