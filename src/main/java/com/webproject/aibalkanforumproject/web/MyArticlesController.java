package com.webproject.aibalkanforumproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/my-articles")
public class MyArticlesController {

    @GetMapping
    public String getMyArticlesPage(Model model){
        model.addAttribute("bodyContent","my-articles");
        return "master-template";
    }
}
