package com.webproject.aibalkanforumproject.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Company {


    @Id
    String name;

    String description;

    String webPage;

    String email;

    public Company() {
    }

    public Company(String name, String webPage, String email,String description) {
        this.name = name;
        this.webPage = webPage;
        this.email = email;
        this.description = description;
    }
}
