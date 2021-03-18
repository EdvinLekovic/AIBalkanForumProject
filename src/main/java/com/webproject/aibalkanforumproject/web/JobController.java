package com.webproject.aibalkanforumproject.web;

import com.webproject.aibalkanforumproject.model.*;
import com.webproject.aibalkanforumproject.model.enumerations.JobType;
import com.webproject.aibalkanforumproject.service.*;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/jobs")
public class JobController {

    private final JobService jobService;
    private final CompanyService companyService;
    private final LocationService locationService;
    private final CategoryService categoryService;
    private final ImageService imageService;

    public JobController(JobService jobService,
                         CompanyService companyService,
                         LocationService locationService,
                         CategoryService categoryService,
                         ImageService imageService) {
        this.jobService = jobService;
        this.companyService = companyService;
        this.locationService = locationService;
        this.categoryService = categoryService;
        this.imageService = imageService;
    }

    @GetMapping
    public String getJobsPage(Model model,HttpServletRequest request){

        Long categoryIdFilter = (Long) request.getSession().getAttribute("categoryIdFilter");
        JobType jobTypeFilter =(JobType) request.getSession().getAttribute("jobTypeFilter");
        Long jobLocationIdFilter = (Long) request.getSession().getAttribute("jobLocationIdFilter");
        List<Job> jobs = jobService.jobsFilter(categoryIdFilter,jobTypeFilter,jobLocationIdFilter);
        addToModelCompaniesLocationsAndCategoriesJobTypes(model);
        model.addAttribute("jobs",jobs);
        model.addAttribute("bodyContent","job-list");
        return "master-template";
    }

    @PostMapping("/filter")
    public String getCountryName(@RequestParam(required = false) Long categoryIdFilter,
                                 @RequestParam(required = false) JobType jobTypeFilter,
                                 @RequestParam(required = false) Long jobLocationIdFilter,
                                 HttpServletRequest request){
        request.getSession().setAttribute("categoryIdFilter",categoryIdFilter);
        request.getSession().setAttribute("jobTypeFilter",jobTypeFilter);
        request.getSession().setAttribute("jobLocationIdFilter",jobLocationIdFilter);
        return "redirect:/jobs";
    }

    private void addToModelCompaniesLocationsAndCategoriesJobTypes(Model model) {
        List<Company> companies = companyService.findAll();
        List<Location> locations = locationService.findAll();
        List<Category> categories = categoryService.listCategories();
        List<JobType> jobTypes = List.of(JobType.FULL_TIME,JobType.PART_TIME,JobType.FREELANCE,JobType.REMOTE);
        model.addAttribute("jobTypes",jobTypes);
        model.addAttribute("categories",categories);
        model.addAttribute("companies",companies);
        model.addAttribute("locations",locations);
    }

    @GetMapping("/info/{id}")
    public String getJobInfo(@PathVariable Long id, Model model){
        Job job = jobService.findById(id);
        model.addAttribute("job",job);
        model.addAttribute("bodyContent","job-info");
        return "master-template";
    }

    @GetMapping("/image/{id}")
    public void showProductImage(@PathVariable Long id,
                                 HttpServletResponse response) throws IOException {
        response.setContentType("image/*");

        Job job = jobService.findById(id);
        InputStream is = new ByteArrayInputStream(job.getImage().getData());
        IOUtils.copy(is, response.getOutputStream());
    }

    @GetMapping("/add-form")
    public String jobFormPage(Model model){
        addToModelCompaniesLocationsAndCategoriesJobTypes(model);
        model.addAttribute("bodyContent","jobs-form");
        return "master-template";
    }

    @PostMapping("/add")
    public String addJob(@RequestParam(required = false) Long id,
                         @RequestParam String companyId,
                         @RequestParam String title,
                         @RequestParam JobType jobType,
                         @RequestParam Long jobCategoryId,
                         @RequestParam Long jobLocationId,
                         @RequestParam String jobSalary,
                         @RequestParam String jobDescription,
                         @RequestParam String knowLedgeSkillsAndAbilities,
                         @RequestParam String educationAndExperience,
                         @RequestParam MultipartFile urlImage,
                         @RequestParam String datepicker) throws IOException, ParseException {
        Image image = null;
        Date date = null;
        if(datepicker!=null&&!datepicker.isEmpty()){
            date = new SimpleDateFormat("MM/dd/yyyy").parse(datepicker);
        }
        if(id!=null){
            if(urlImage.getOriginalFilename().isEmpty()) {
                jobService.edit(id,
                        companyId,
                        title,
                        jobType,
                        jobDescription,
                        knowLedgeSkillsAndAbilities,
                        educationAndExperience,
                        jobSalary,
                        jobLocationId,
                        date,
                        jobCategoryId,
                        null);
            }
            else{
                Long imageId = jobService.findById(id).getImage().getId();
                image = imageService.store(urlImage);
                jobService.edit(id,
                        companyId,
                        title,
                        jobType,
                        jobDescription,
                        knowLedgeSkillsAndAbilities,
                        educationAndExperience,
                        jobSalary,
                        jobLocationId,
                        date,
                        jobCategoryId,
                        image);
                imageService.delete(imageId);
            }

        }
        else {
            image = imageService.store(urlImage);
            jobService.create(companyId,
                    title,
                    jobType,
                    jobDescription,
                    knowLedgeSkillsAndAbilities,
                    educationAndExperience,
                    jobSalary,
                    jobLocationId,
                    date,
                    jobCategoryId,
                    image);
        }
        return "redirect:/jobs";
    }

    @GetMapping("/{id}/edit")
    public String editJob(@PathVariable Long id,Model model){
        Job job = jobService.findById(id);
        addToModelCompaniesLocationsAndCategoriesJobTypes(model);
        model.addAttribute("job",job);
        model.addAttribute("bodyContent","jobs-form");
        return "master-template";
    }

    @GetMapping("/{id}/delete")
    public String deleteJob(@PathVariable Long id){
        imageService.delete(jobService.findById(id).getImage().getId());
        jobService.delete(id);
        return "redirect:/jobs";
    }

}
