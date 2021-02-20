package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

//Made by Edvin Lekovic
@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findAllByJobTitleContains(String jobTitle);
    List<Job> findAllByCompanyContains(String company);
    List<Job> findJobsByCategoryContains(Category category);
    List<Job> findJobsByDateCreatedContains(LocalDateTime dateTime);
}
