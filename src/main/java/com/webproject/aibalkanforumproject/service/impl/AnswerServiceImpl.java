package com.webproject.aibalkanforumproject.service.impl;


import com.webproject.aibalkanforumproject.model.Answer;
import com.webproject.aibalkanforumproject.model.Question;
import com.webproject.aibalkanforumproject.model.User;
import com.webproject.aibalkanforumproject.model.exceptions.AnswerNotFoundException;
import com.webproject.aibalkanforumproject.model.exceptions.QuestionNotFoundException;
import com.webproject.aibalkanforumproject.model.exceptions.UserNotExistException;
import com.webproject.aibalkanforumproject.repository.AnswerRepository;
import com.webproject.aibalkanforumproject.repository.QuestionRepository;
import com.webproject.aibalkanforumproject.repository.UserRepository;
import com.webproject.aibalkanforumproject.service.AnswerService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    public AnswerServiceImpl(AnswerRepository answerRepository, UserRepository userRepository, QuestionRepository questionRepository) {
        this.answerRepository = answerRepository;
        this.userRepository = userRepository;
        this.questionRepository = questionRepository;
    }


    @Override
    public Answer create(Long questionId, String description, String username) {
        Question question = questionRepository.findById(questionId).orElseThrow(()-> new QuestionNotFoundException(questionId));
        User user = userRepository.findById(username).orElseThrow(()->new UsernameNotFoundException(username));
        return answerRepository.save(new Answer(description,question,user));
    }

    @Override
    public Answer delete(Long id) {
        Answer answer = this.answerRepository.findById(id).orElseThrow(() -> new AnswerNotFoundException(id));
        this.answerRepository.deleteById(id);
        return answer;
    }

    @Override
    public List<Answer> searchAnswersByDescription(String keyWord) {
        return this.answerRepository.findAllByDescriptionContaining(keyWord);
    }

    @Override
    public List<Answer> searchAnswersByUser(String username) {

        User user = this.userRepository.findById(username).orElseThrow(() -> new UserNotExistException(username));
        return this.answerRepository.findAnswersByUser(user);
    }

    @Override
    public List<Answer> searchAllAnswersToQuestion(Long id) {

        Question question = this.questionRepository.findById(id).orElseThrow(() -> new QuestionNotFoundException(id));
        if(question == null){
            return Collections.emptyList();
        }
        return this.answerRepository.findAllByQuestion(question);
    }

    @Override
    public List<Answer> searchAnswersByQuestion(Question question) {
        return answerRepository.findAllByQuestion(question);
    }

    @Override
    public List<Answer> searchAnswersByDescriptionLike(String description) {
        return answerRepository.findAnswersByDescriptionContains(description);
    }

    @Override
    public List<Answer> searchAnswersByQuestionAndDescriptionLike(Question question, String description) {
        return answerRepository.findAnswersByQuestionAndDescriptionContains(question.getId(),description);
    }


}
