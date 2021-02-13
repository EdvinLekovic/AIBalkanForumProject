package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Answer;
import com.webproject.aibalkanforumproject.model.Question;

import java.util.List;

//Made by Filip Stavrov

public interface AnswerService {

    Answer create(String description, Long questionId);
    Answer delete(Long id);
    List<Answer> searchAnswersByDescription(String keyWord);
    List<Answer> searchAnswersByUser(String username);
    List<Answer> searchAllAnswersToQuestion(Long id);

}
