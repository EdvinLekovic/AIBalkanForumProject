package com.webproject.aibalkanforumproject.web;

import com.webproject.aibalkanforumproject.model.Answer;
import com.webproject.aibalkanforumproject.model.Question;
import com.webproject.aibalkanforumproject.service.AnswerService;
import com.webproject.aibalkanforumproject.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/myAnswers")
public class MyAnswersController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    public MyAnswersController(AnswerService answerService, QuestionService questionService) {
        this.answerService = answerService;
        this.questionService = questionService;
    }

    @GetMapping
    public String getMyQuestionsPage(Model model, HttpServletRequest request) {

        String username = request.getRemoteUser();
        List<Answer> answers = this.answerService.searchAnswersByUser(username);
        List<Question> questions = answers.stream().map(Answer::getQuestion).collect( Collectors.toList());

        model.addAttribute("questions", questions);
        model.addAttribute("answers", answers);
        model.addAttribute("bodyContent", "myAnswers");
        return "master-template";
    }
}
