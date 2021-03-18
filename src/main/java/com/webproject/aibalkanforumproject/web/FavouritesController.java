package com.webproject.aibalkanforumproject.web;

import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Favourite;
import com.webproject.aibalkanforumproject.model.exceptions.ArticleAlreadyInFavourites;
import com.webproject.aibalkanforumproject.service.FavouriteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/favourites")
public class FavouritesController {

    private final FavouriteService favouriteService;

    public FavouritesController(FavouriteService favouriteService) {
        this.favouriteService = favouriteService;
    }


    @GetMapping
    public String getFavouritesPage(Model model, HttpServletRequest request, @RequestParam(required = false) String error) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        String username = (request.getSession().getAttribute("email")) == null ?
                request.getRemoteUser() : ((String) request.getSession().getAttribute("email"));
        Favourite favourite;
        favourite = this.favouriteService.getFavourite(username);

        List<Article> articles = this.favouriteService.listAllArticlesInFavourite(favourite.getId());

        model.addAttribute("articles", articles);
        model.addAttribute("bodyContent", "favourites");

        return "master-template";
    }

    @PostMapping("/add-article/{id}")
    public String addArticleFavourite(@PathVariable Long id, HttpServletRequest request) {
        try {
            String username = (request.getSession().getAttribute("email")) == null ?
                    request.getRemoteUser() : ((String) request.getSession().getAttribute("email"));
            this.favouriteService.addArticleToFavourites(username, id);

        } catch (ArticleAlreadyInFavourites exception) {
            return "redirect:/favourites?error=" + exception.getMessage();
        }
        return "redirect:/favourites";
    }


    @PostMapping("/{id}/delete")
    public String deleteArticleFavourite(@PathVariable Long id, HttpServletRequest request) {
        String username = (request.getSession().getAttribute("email")) == null ? request.getRemoteUser() : ((String) request.getSession().getAttribute("email"));
        favouriteService.delete(id, username);
        return "redirect:/favourites";
    }
}
