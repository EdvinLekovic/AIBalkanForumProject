package com.webproject.aibalkanforumproject.service;




import com.webproject.aibalkanforumproject.model.Question;

import java.util.List;

public interface QuestionService {

    Question create(String title, String description,String username);
    Question edit(Long id, String title, String description);
    Question delete(Long id);
    List<Question> findAllQuestionsWithTitleLike(String title);
    List<Question> searchQuestionsByDescription(String keyWord);
    List<Question> searchQuestionsByUser(String username);
    List<Question> findAllQuestions();
    Question searchQuestionById(Long id);
    List<Question> searchQuestionsByTitleAndDescriptionLike(String titleAndDesc);
    List<Question> searchQuestionsByUsernameAndTitleAndDescriptionLike(String username,String titleAndDesc);
    Question searchQuestionByIdAndTitleAndDescriptionLike(Long id,String titleAndDesc);
}
