package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.Answer;
import com.webproject.aibalkanforumproject.model.Question;
import com.webproject.aibalkanforumproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//Made by Filip Stavrov

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    List<Answer> findAllByDescriptionContaining(String keyWord);
    //List<Answer> findAnswersByUser(User user);
    List<Answer> findAllByQuestion(Question question);

}
