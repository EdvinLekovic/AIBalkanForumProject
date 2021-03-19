package com.webproject.aibalkanforumproject.web;

import com.webproject.aibalkanforumproject.model.Answer;
import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Question;
import com.webproject.aibalkanforumproject.service.AnswerService;
import com.webproject.aibalkanforumproject.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
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

        String username = (request.getSession().getAttribute("email")) == null ?
                request.getRemoteUser() : ((String) request.getSession().getAttribute("email"));

        Map<Long,List<Answer>> questionAnswerMap =
                this.answerService.searchAllUserAnswersPerQuestion(username);
        Set<Long> questionKeySet = questionAnswerMap.keySet();
        model.addAttribute("questionKeySet",questionKeySet);
        model.addAttribute("questionAnswerMap", questionAnswerMap);
        model.addAttribute("questionService",questionService);
        model.addAttribute("bodyContent", "myAnswers");
        return "master-template";
    }

    @PostMapping("/delete-answer/{answerId}")
    public String deleteAnswer(@PathVariable Long answerId) {
        answerService.delete(answerId);
        return "redirect:/myAnswers";
    }

}
