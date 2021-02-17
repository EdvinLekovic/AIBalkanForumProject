package com.webproject.aibalkanforumproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/favourites")
public class FavouritesController {

    @GetMapping
    public String getFavouritesPage(Model model){
        model.addAttribute("bodyContent","favourites");
        return "master-template";
    }
}
