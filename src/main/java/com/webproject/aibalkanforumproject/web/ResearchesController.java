package com.webproject.aibalkanforumproject.web;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/researches")
public class ResearchesController {

    private final ArticleService articleService;

    public ResearchesController(ArticleService articleService) {
        this.articleService = articleService;
    }


    @GetMapping
    public String getResearchesPage(Model model){

        List<Article> articles = this.articleService.findAll();

        model.addAttribute("articles", articles);
        model.addAttribute("bodyContent","researches");

        return "master-template";
    }


    @GetMapping("/{id}/readMore")
    public String readArticle(@PathVariable Long id, Model model) throws IOException {
        Article article = this.articleService.findById(id);
        model.addAttribute("article",article);
        model.addAttribute("bodyContent", "article");
        return "master-template";
    }

}
