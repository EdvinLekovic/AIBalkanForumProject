package com.webproject.aibalkanforumproject.service.impl;

import com.webproject.aibalkanforumproject.model.*;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidCategoryIdException;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidCompanyNameException;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidJobIdException;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidLocationIdException;
import com.webproject.aibalkanforumproject.repository.CategoryRepository;
import com.webproject.aibalkanforumproject.repository.CompanyRepository;
import com.webproject.aibalkanforumproject.repository.JobRepository;
import com.webproject.aibalkanforumproject.repository.LocationRepository;
import com.webproject.aibalkanforumproject.service.JobService;
import org.springframework.stereotype.Service;

import javax.persistence.Transient;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final LocationRepository locationRepository;
    private final CategoryRepository categoryRepository;

    public JobServiceImpl(JobRepository jobRepository,
                          CompanyRepository companyRepository,
                          LocationRepository locationRepository, CategoryRepository categoryRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.locationRepository = locationRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public List<Job> findByJobTitle(String jobTitle) {
        return jobRepository.findAllByTitleContains(jobTitle);
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

    @Override
    public Job findById(Long id) {
        return jobRepository.findById(id).orElseThrow(()->new InvalidJobIdException(id));
    }

    @Override
    public Job create(String companyId,
                    String title,
                    JobType jobType,
                    String description,
                    String knowLedgeSkillsAndAbilities,
                    String experience,
                    String salary,
                    Long locationId,
                    Date deadlineApply,
                    Long categoryId,
                    Image image) {
        Company company = companyRepository.findById(companyId).orElseThrow(()->new InvalidCompanyNameException(companyId));
        Location location = locationRepository.findById(locationId).orElseThrow(()->new InvalidLocationIdException(locationId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new InvalidCategoryIdException(categoryId));
        LocalDateTime localDateTime = LocalDateTime.ofInstant(deadlineApply.toInstant(), ZoneId.systemDefault());
        Job job = new Job(company,
                title,
                jobType,
                description,
                knowLedgeSkillsAndAbilities,
                experience,
                salary,
                location,
                localDateTime,
                category,
                image);
        return jobRepository.save(job);
    }

    @Override
    public Job edit(Long id,
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
                    Image image) {
        Job job = jobRepository.findById(id).orElseThrow(()->new InvalidJobIdException(id));
        Company company = companyRepository.findById(companyId).orElseThrow(()->new InvalidCompanyNameException(companyId));
        Location location = locationRepository.findById(locationId).orElseThrow(()->new InvalidLocationIdException(locationId));
        Category category = categoryRepository.findById(categoryId).orElseThrow(()->new InvalidCategoryIdException(categoryId));
        LocalDateTime localDateTime = LocalDateTime.ofInstant(deadlineApply.toInstant(), ZoneId.systemDefault());
        job.setCompany(company);
        job.setTitle(title);
        job.setJobType(jobType);
        job.setDescription(description);
        job.setKnowLedgeSkillsAndAbilities(knowLedgeSkillsAndAbilities);
        job.setExperience(experience);
        job.setSalary(salary);
        job.setLocation(location);
        job.setDeadlineApply(localDateTime);
        job.setCategory(category);
        if(image!=null) {
            job.setImage(image);
        }
        return jobRepository.save(job);
    }

    @Override
    public Job delete(Long id) {
        Job job = jobRepository.findById(id).orElseThrow(()->new InvalidJobIdException(id));
        jobRepository.delete(job);
        return job;
    }

    @Override
    public List<Job> jobsFilter(Long categoryIdFilter,JobType jobTypeFilter,Long jobLocationIdFilter){


        if (categoryIdFilter!=null&&jobTypeFilter==null&&jobLocationIdFilter==null){
           Category category =  categoryRepository.findById(categoryIdFilter)
                   .orElseThrow(()-> new InvalidCategoryIdException(categoryIdFilter));
            return jobRepository.findJobsByCategory(category);
        }
        else if (categoryIdFilter!=null&&jobTypeFilter!=null&&jobLocationIdFilter==null){
            Category category =  categoryRepository.findById(categoryIdFilter)
                    .orElseThrow(()-> new InvalidCategoryIdException(categoryIdFilter));
            return jobRepository.findJobsByCategoryAndJobType(category,jobTypeFilter);
        }
        else if (categoryIdFilter!=null&&jobTypeFilter!=null&&jobLocationIdFilter!=null){
            Category category =  categoryRepository.findById(categoryIdFilter)
                    .orElseThrow(()-> new InvalidCategoryIdException(categoryIdFilter));
            Location location = locationRepository.findById(jobLocationIdFilter)
                    .orElseThrow(()->new InvalidLocationIdException(jobLocationIdFilter));
            return jobRepository.findJobsByCategoryAndJobTypeAndLocation(category,jobTypeFilter,location);
        }
        else if (categoryIdFilter==null&&jobTypeFilter!=null&&jobLocationIdFilter!=null){
            Location location = locationRepository.findById(jobLocationIdFilter)
                    .orElseThrow(()->new InvalidLocationIdException(jobLocationIdFilter));
            return jobRepository.findJobsByJobTypeAndLocation(jobTypeFilter,location);
        }
        else if(categoryIdFilter==null&&jobTypeFilter==null&&jobLocationIdFilter!=null){
            Location location = locationRepository.findById(jobLocationIdFilter)
                    .orElseThrow(()->new InvalidLocationIdException(jobLocationIdFilter));
            return jobRepository.findJobsByLocation(location);
        }
        else if (categoryIdFilter==null&&jobTypeFilter!=null&&jobLocationIdFilter==null){
            return jobRepository.findJobsByJobType(jobTypeFilter);
        }
        else if(categoryIdFilter!=null&&jobTypeFilter==null&&jobLocationIdFilter!=null){
            Category category =  categoryRepository.findById(categoryIdFilter)
                    .orElseThrow(()-> new InvalidCategoryIdException(categoryIdFilter));
            Location location = locationRepository.findById(jobLocationIdFilter)
                    .orElseThrow(()->new InvalidLocationIdException(jobLocationIdFilter));

            return jobRepository.findJobsByCategoryAndLocation(category,location);
        }
        return findAll();
    }

}
