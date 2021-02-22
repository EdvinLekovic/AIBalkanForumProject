package com.webproject.aibalkanforumproject.web;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Category;
import com.webproject.aibalkanforumproject.model.Image;
import com.webproject.aibalkanforumproject.service.ArticleService;
import com.webproject.aibalkanforumproject.service.CategoryService;
import com.webproject.aibalkanforumproject.service.ImageService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("myArticles")
public class MyArticlesController {

    private final ArticleService articleService;
    private final CategoryService categoryService;
    private final ImageService imageService;

    public MyArticlesController(ArticleService articleService, CategoryService categoryService, ImageService imageService) {
        this.articleService = articleService;
        this.categoryService = categoryService;
        this.imageService = imageService;
    }

    @GetMapping
    public String getMyArticlesPage(Model model,HttpServletRequest request) {

        String username = request.getRemoteUser();
        List<Article> articles = this.articleService.findByUser(username);

        model.addAttribute("articles", articles);
        model.addAttribute("bodyContent", "my-articles");
        return "master-template";
    }

    @GetMapping("/image/{id}")
    public void showProductImage(@PathVariable Long id,
                               HttpServletResponse response) throws IOException {
        response.setContentType("image/*");

        Article article = articleService.findById(id);

        InputStream is = new ByteArrayInputStream(article.getImage().getData());
        IOUtils.copy(is, response.getOutputStream());
    }

    @GetMapping("/add-form")
    public String getAddProductPage(Model model){
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "addResearch");

        return "master-template";
    }

    @PostMapping("/add")
    public String addArticle(@RequestParam(required = false) Long id,
                             @RequestParam String title,
                             @RequestParam String description,
                             @RequestParam Long categoryId,
                             @RequestParam MultipartFile urlImage,
                             @RequestParam String username) throws IOException {
        Image image = imageService.store(urlImage);
        if(id!=null){
            articleService.edit(id,title,description,categoryId,image);
        }
        else {
            this.articleService.create(title, description, categoryId, image, username);
        }
        return "redirect:/myArticles";
    }

    @PostMapping("/{id}/delete")
    public String deleteArticle(@PathVariable Long id){
        this.articleService.delete(id);
        return "redirect:/myArticles";
    }

    @GetMapping("/{id}/edit")
    public String editArticle(@PathVariable Long id,Model model) throws IOException {
        List<Category> categories = this.categoryService.listCategories();
        Article article = articleService.findById(id);
        model.addAttribute("article",article);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "addResearch");
        return "master-template";
    }

}

