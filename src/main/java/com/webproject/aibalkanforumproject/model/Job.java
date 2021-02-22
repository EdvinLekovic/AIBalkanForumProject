package com.webproject.aibalkanforumproject.model;

import lombok.Data;


import javax.persistence.*;
import java.time.LocalDateTime;
//Made by Edvin Lekovic
@Data
@Entity
public class Job {

    @Id
    private Long id;

    private String company;

    private String jobTitle;

    @Enumerated(value = EnumType.STRING)
    private JobType jobType;

    private String experience;

    private String salary;

    private LocalDateTime dateCreated;

    private LocalDateTime deadlineApply;

    private String urlImage;

    @ManyToOne
    private Category category;

    public Job() {
    }


    public Job(Long id, String jobTitle, JobType jobType, String experience, String company, Category category,String salary,LocalDateTime deadlineApply) {
        this.id = id;
        this.jobTitle = jobTitle;
        this.jobType = jobType;
        this.experience = experience;
        this.company = company;
        this.category = category;
        this.salary = salary;
        this.dateCreated = LocalDateTime.now();
        this.deadlineApply = deadlineApply;
    }
}
