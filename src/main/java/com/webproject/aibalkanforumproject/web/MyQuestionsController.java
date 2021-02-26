package com.webproject.aibalkanforumproject.web;

import com.webproject.aibalkanforumproject.model.Question;
import com.webproject.aibalkanforumproject.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/myQuestions")
public class MyQuestionsController {

    private final QuestionService questionService;

    public MyQuestionsController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public String getMyQuestionsPage(Model model, HttpServletRequest request) {

        String username = request.getRemoteUser();
        List<Question> questions = this.questionService.searchQuestionsByUser(username);

        model.addAttribute("questions", questions);
        model.addAttribute("bodyContent", "myQuestions");
        return "master-template";
    }
}
