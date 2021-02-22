package com.webproject.aibalkanforumproject.web;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.service.ArticleService;
import com.webproject.aibalkanforumproject.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/researches")
public class ResearchesController {



    @GetMapping
    public String getResearchesPage(Model model){
        model.addAttribute("bodyContent","researches");
        return "master-template";
    }

}
