package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.Job;
import com.webproject.aibalkanforumproject.model.JobType;
import com.webproject.aibalkanforumproject.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobRepository extends JpaRepository<Job,Long> {
    List<Job> findAllByTitleContains(String jobTitle);
    List<Job> findAllByCompanyContains(String company);
    List<Job> findJobsByCategoryContains(Category category);
    List<Job> findJobsByDateCreatedContains(LocalDateTime dateTime);
    List<Job> findJobsByCategoryAndJobType(Category category, JobType jobType);
    List<Job> findJobsByCategoryAndJobTypeAndLocation(Category category, JobType jobType, Location location);
    List<Job> findJobsByJobTypeAndLocation(JobType jobType,Location location);
    List<Job> findJobsByLocation(Location location);
    List<Job> findJobsByJobType(JobType jobType);
    List<Job> findJobsByCategoryAndLocation(Category category,Location location);
    List<Job> findJobsByCategory(Category category);
}
