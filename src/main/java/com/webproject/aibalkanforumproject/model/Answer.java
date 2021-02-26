package com.webproject.aibalkanforumproject.model;


import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Data
@Entity
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne()
    @JoinColumn(
            foreignKey = @ForeignKey(
                    name = "question_id",
                    foreignKeyDefinition = "FOREIGN KEY (question_id) " +
                            "REFERENCES question(id) " +
                            "ON UPDATE CASCADE ON DELETE CASCADE"
            )
    )
    private Question question;

    @ManyToOne
    private User user;

    public Answer() {
    }

    public Answer(String description, Question question,User user) {
        this.description = description;
        this.question = question;
        this.user = user;
    }
}
