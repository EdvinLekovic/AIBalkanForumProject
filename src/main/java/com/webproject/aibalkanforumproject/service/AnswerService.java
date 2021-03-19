package com.webproject.aibalkanforumproject.service;

import com.webproject.aibalkanforumproject.model.Answer;
import com.webproject.aibalkanforumproject.model.Question;

import java.util.List;
import java.util.Map;


public interface AnswerService {

    Answer create(Long questionId,String description,String username);
    Answer delete(Long id);
    List<Answer> searchAnswersByDescription(String keyWord);
    List<Answer> searchAnswersByUser(String username);
    List<Answer> searchAllAnswersToQuestion(Long id);
    List<Answer> searchAnswersByQuestion(Question question);
    List<Answer> searchAnswersByDescriptionLike(String description);
    List<Answer> searchAnswersByQuestionAndDescriptionLike(Question question,String description);
    Map<Long,List<Answer>> searchAllUserAnswersPerQuestion(String username);
}
