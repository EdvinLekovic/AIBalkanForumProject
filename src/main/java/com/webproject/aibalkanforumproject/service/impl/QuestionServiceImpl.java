package com.webproject.aibalkanforumproject.service.impl;


import com.webproject.aibalkanforumproject.model.Article;
import com.webproject.aibalkanforumproject.model.Question;
import com.webproject.aibalkanforumproject.model.User;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidArticleIdException;
import com.webproject.aibalkanforumproject.model.exceptions.InvalidTitleException;
import com.webproject.aibalkanforumproject.model.exceptions.QuestionNotFoundException;
import com.webproject.aibalkanforumproject.model.exceptions.UserNotExistException;
import com.webproject.aibalkanforumproject.repository.QuestionRepository;
import com.webproject.aibalkanforumproject.repository.UserRepository;
import com.webproject.aibalkanforumproject.service.QuestionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, UserRepository userRepository) {
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Question create(String title, String description) {
        if(title.isEmpty() || description.isEmpty()){
            throw new IllegalArgumentException();
        }
        return new Question(title, description);
    }

    @Override
    public Question edit(Long id, String title, String description) {
        Question question = this.questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
        question.setTitle(title);
        question.setDescription(description);
        return this.questionRepository.save(question);
    }

    @Override
    public Question delete(Long id) {
        Question question = this.questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
        this.questionRepository.deleteById(id);
        return question;
    }

    @Override
    public List<Question> findAllQuestionsWithTitleLike(String title) {
        return this.questionRepository.findAllByTitleLike(title);
    }

    @Override
    public List<Question> searchQuestionsByDescription(String keyWord) {
        return this.questionRepository.findAllByDescriptionContaining(keyWord);
    }

    @Override
    public List<Question> searchQuestionsByUser(String username) {
        User user = this.userRepository.findById(username).orElseThrow(() -> new UserNotExistException(username));
        return this.questionRepository.findQuestionsByUser(user);
    }
}
