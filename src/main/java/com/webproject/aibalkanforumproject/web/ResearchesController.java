package com.webproject.aibalkanforumproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/researches")
public class ResearchesController {

    @GetMapping
    public String getResearchesPage(Model model){
        model.addAttribute("bodyContent","researches");
        return "master-template";
    }


//    @GetMapping
//    public String getResearchRead(Model model){
//    return "";
//    }
}
