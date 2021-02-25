package com.webproject.aibalkanforumproject.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/events")
public class EventsController {

    @GetMapping
    public String getHomePage(Model model, HttpServletRequest request) {
        model.addAttribute("bodyContent", "events");
        return "master-template";
    }
}
