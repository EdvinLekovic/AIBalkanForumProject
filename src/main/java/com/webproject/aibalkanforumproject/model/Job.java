package com.webproject.aibalkanforumproject.model;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
//Made by Edvin Lekovic
@Data
@Entity
public class Job {

    @Id
    Long id;

    String company;

    String jobTitle;

    JobType jobType;

    String experience;

    String salary;

    LocalDateTime dateCreated;

    LocalDateTime deadlineApply;

    String urlImage;

    @ManyToOne
    Category category;

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
