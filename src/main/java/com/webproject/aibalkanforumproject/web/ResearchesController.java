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

    private final CategoryService categoryService;
    private final ArticleService articleService;

    public ResearchesController(CategoryService categoryService, ArticleService articleService) {
        this.categoryService = categoryService;
        this.articleService = articleService;
    }


    @GetMapping
    public String getResearchesPage(Model model){
        model.addAttribute("bodyContent","researches");
        return "master-template";
    }

    @GetMapping("/myArticles")
    public String getMyArticlesPage(Model model){

        List<Article> articles = this.articleService.findAll();
        model.addAttribute("articles", articles);
        model.addAttribute("bodyContent", "my-articles");
        return "master-template";
    }

    @GetMapping("/add-form")
    public String getAddProductPage(Model model){
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "addResearch");

        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(@RequestParam String title, @RequestParam String description,
                              @RequestParam String urlImage, @RequestParam Long categoryId,
                              @RequestParam String userId){

        this.articleService.create(title, description, urlImage, categoryId, userId);
        return "redirect:/my-articles";

    }

    @GetMapping("/id")
    public String getResearchRead(Model model){
        model.addAttribute("bodyContent","article");
        return "master-template";
    }
}
