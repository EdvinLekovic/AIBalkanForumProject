package com.webproject.aibalkanforumproject.service.impl;

import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.Job;
import com.webproject.aibalkanforumproject.repository.JobRepository;
import com.webproject.aibalkanforumproject.service.JobService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> findByJobTitle(String jobTitle) {
        return jobRepository.findAllByJobTitleContains(jobTitle);
    }

    @Override
    public List<Job> findByCompany(String company) {
        return jobRepository.findAllByCompanyContains(company);
    }

    @Override
    public List<Job> findByCategory(Category category) {
        return jobRepository.findJobsByCategoryContains(category);
    }

    @Override
    public List<Job> findByDate(LocalDateTime dateTime) {
        return jobRepository.findJobsByDateCreatedContains(dateTime);
    }
}
