package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.*;
import com.webproject.aibalkanforumproject.model.enumerations.JobType;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public interface JobService {
    List<Job> findAll();
    List<Job> findByJobTitle(String jobTitle);
    List<Job> findByCompany(String company);
    List<Job> findByCategory(Category category);
    List<Job> findByDate(LocalDateTime dateTime);
    Job findById(Long id);
    Job create(String companyId,
             String title,
             JobType jobType,
             String description,
             String knowLedgeSkillsAndAbilities,
             String experience,
             String salary,
             Long locationId,
             Date deadlineApply,
             Long categoryId,
             Image image);
    Job edit(Long id,
            String companyId,
             String title,
             JobType jobType,
             String description,
             String knowLedgeSkillsAndAbilities,
             String experience,
             String salary,
             Long locationId,
             Date deadlineApply,
             Long categoryId,
             Image image);
    Job delete(Long id);
    List<Job> jobsFilter(Long categoryIdFilter,JobType jobTypeFilter,Long jobLocationIdFilter);
}
