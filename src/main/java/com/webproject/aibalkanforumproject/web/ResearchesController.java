package com.webproject.aibalkanforumproject.web;

import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/researches")
public class ResearchesController {

    private final CategoryService categoryService;

    public ResearchesController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping
    public String getResearchesPage(Model model){
        model.addAttribute("bodyContent","researches");
        return "master-template";
    }

    @GetMapping("/add-form")
    public String getAddProductPage(Model model){
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "addResearch");

        return "master-template";
    }

    @GetMapping("/id")
    public String getResearchRead(Model model){
        model.addAttribute("bodyContent","article");
        return "master-template";
    }
}
