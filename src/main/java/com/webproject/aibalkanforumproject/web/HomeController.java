package com.webproject.aibalkanforumproject.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/home")
public class HomeController {

    @GetMapping("/")
    String getHomePage(){
        return "master-template";
    }
}
