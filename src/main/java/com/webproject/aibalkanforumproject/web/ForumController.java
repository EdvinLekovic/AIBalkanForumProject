package com.webproject.aibalkanforumproject.web;

import com.webproject.aibalkanforumproject.model.Answer;
import com.webproject.aibalkanforumproject.model.Question;
import com.webproject.aibalkanforumproject.service.AnswerService;
import com.webproject.aibalkanforumproject.service.QuestionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/forum")
public class ForumController {

    private final QuestionService questionService;
    private final AnswerService answerService;

    public ForumController(QuestionService questionService, AnswerService answerService) {
        this.questionService = questionService;
        this.answerService = answerService;
    }


    @GetMapping
    public String getForumPage(Model model,HttpServletRequest request){
        String titleAndDesc = (String) request.getSession().getAttribute("titleAndDesc");
        List<Question> questions;
        if(titleAndDesc!=null){
            questions = questionService.searchQuestionsByTitleAndDescriptionLike(titleAndDesc);
        }
        else{
            questions = questionService.findAllQuestions();
        }
        model.addAttribute("bodyContent","forum");
        model.addAttribute("answerService",answerService);
        model.addAttribute("questions",questions);
        return "master-template";
    }

    @GetMapping("/info/{id}")
    public String getForumInfoPage(@PathVariable Long id,Model model,HttpServletRequest request){
        String titleAndDesc = (String) request.getSession().getAttribute("titleAndDesc");
        Question question = questionService.searchQuestionById(id);
        List<Answer> answers;
        if(titleAndDesc!=null){
            answers = answerService.
                    searchAnswersByQuestionAndDescriptionLike(question,titleAndDesc);
        }
        else{
            answers = answerService.searchAnswersByQuestion(question);
        }
        model.addAttribute("question",question);
        model.addAttribute("numAnswers",answers.size());
        model.addAttribute("answers",answers);
        model.addAttribute("bodyContent","forum-info");
        return "master-template";
    }


    @PostMapping("/addQuestion")
    public String addQuestion(
            @RequestParam(required = false) Long id,
            @RequestParam String questionTitle,
            @RequestParam String questionText,
            @RequestParam String username){
        if(id!=null){
            questionService.edit(id,questionTitle,questionText);
        }
        else {
            questionService.create(questionTitle, questionText, username);
        }
        return "redirect:/forum";
    }

    @PostMapping("/addAnswer/{id}")
    public String addAnswer(
            @PathVariable Long id,
            @RequestParam String questionText,
            @RequestParam String username){
        answerService.create(id,questionText,username);
        return "redirect:/forum/info/"+id;
    }

    @GetMapping("/edit-question/{id}")
    public String editQuestion(@PathVariable Long id,Model model){
        Question question = questionService.searchQuestionById(id);
        model.addAttribute("question",question);
        model.addAttribute("bodyContent","forum-edit-form");
        return "master-template";
    }

    @PostMapping("/delete-question/{id}")
    public String deleteQuestion(@PathVariable Long id){
        Question question = questionService.searchQuestionById(id);
        List<Answer> answers = answerService.searchAnswersByQuestion(question);
        for(int i = 0;i<answers.size();i++){
            answerService.delete(answers.get(i).getId());
        }
        questionService.delete(id);
        return "redirect:/forum";
    }

    @PostMapping("/delete-answer/{answerId}/{questionId}")
    public String deleteAnswer(@PathVariable Long answerId,
                               @PathVariable Long questionId){
        answerService.delete(answerId);
        return "redirect:/forum/info/"+questionId;
    }


    @PostMapping("/questionFilter")
    public String questionFilter(@RequestParam String titleAndDesc,
                                 HttpServletRequest request){
        request.getSession().setAttribute("titleAndDesc",titleAndDesc);
        return "redirect:/forum";
    }

    @PostMapping("/questionAndAnswerFilter/{id}")
    public String questionAndAnswerFilter(@PathVariable Long id,
                                          @RequestParam String titleAndDesc,
                                          HttpServletRequest request){
        request.getSession().setAttribute("titleAndDesc",titleAndDesc);
        return "redirect:/forum/info/"+id;
    }



}
