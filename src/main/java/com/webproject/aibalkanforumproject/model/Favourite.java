package com.webproject.aibalkanforumproject.model;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @ManyToMany
    private List<Article> articles;

    public Favourite() {
    }

    public Favourite(User user) {
        this.user = user;
        this.articles = new ArrayList<>();
    }
}
