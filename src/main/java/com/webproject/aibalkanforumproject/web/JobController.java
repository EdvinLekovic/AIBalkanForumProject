package com.webproject.aibalkanforumproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jobs")
public class JobController {

    @GetMapping
    public String getJobsPage(Model model){
        model.addAttribute("bodyContent","job-list");
        return "master-template";
    }

    @GetMapping("/info")
    public String getJobInfo(Model model){
        model.addAttribute("bodyContent","job-info");
        return "master-template";
    }

    @GetMapping("/add-form")
    public String jobFormPage(Model model){
        model.addAttribute("bodyContent","jobs-form");
        return "master-template";
    }

//    @PostMapping("/add")
//    public String addJob(Model model){
//        return "master-template";
//    }
}
