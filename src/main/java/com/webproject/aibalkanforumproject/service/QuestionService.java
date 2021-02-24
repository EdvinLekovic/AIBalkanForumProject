package com.webproject.aibalkanforumproject.service;




import com.webproject.aibalkanforumproject.model.Question;

import java.util.List;

public interface QuestionService {

    Question create(String title, String description);
    Question edit(Long id, String title, String description);
    Question delete(Long id);
    List<Question> findAllQuestionsWithTitleLike(String title);
    List<Question> searchQuestionsByDescription(String keyWord);
    List<Question> searchQuestionsByUser(String username);
}
