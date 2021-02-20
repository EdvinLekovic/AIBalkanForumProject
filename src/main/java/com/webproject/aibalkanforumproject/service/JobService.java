package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.Job;

import java.time.LocalDateTime;
import java.util.List;

public interface JobService {
    List<Job> findAll();
    List<Job> findByJobTitle(String jobTitle);
    List<Job> findByCompany(String company);
    List<Job> findByCategory(Category category);
    List<Job> findByDate(LocalDateTime dateTime);
}
