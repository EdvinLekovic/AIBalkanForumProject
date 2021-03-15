package com.webproject.aibalkanforumproject.web;

import com.webproject.aibalkanforumproject.model.enumerations.Role;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidPasswordException;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidUsernameException;
import com.webproject.aibalkanforumproject.model.exceptions.PasswordsDoNotMatchException;
import com.webproject.aibalkanforumproject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/register")
public class RegisterController {

    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getRegisterPage(@RequestParam(required = false) String error, Model model) {
        if(error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        model.addAttribute("bodyContent","register");
        return "master-template";
    }

    @PostMapping
    public String register(@RequestParam String username,
                           @RequestParam String name,
                           @RequestParam String surname,
                           @RequestParam String password,
                           @RequestParam String repeatedPassword) {
        try{
            this.userService.register(username, name, surname, password, repeatedPassword,Role.ROLE_USER);
            return "redirect:/login";
        } catch (InvalidPasswordException | InvalidUsernameException | PasswordsDoNotMatchException exception) {
            return "redirect:/register?error=" + exception.getMessage();
        }
    }


}
