package com.webproject.aibalkanforumproject.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Event")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @Column(name="start")
    private LocalDateTime start;

    @Column(name="end")
    private LocalDateTime finish;


    public Event() {
    }

    public Event(String title, String description, LocalDateTime start, LocalDateTime finish) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.finish = finish;
    }
}
