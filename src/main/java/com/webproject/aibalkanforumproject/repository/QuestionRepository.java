package com.webproject.aibalkanforumproject.repository;


import com.webproject.aibalkanforumproject.model.Question;
import com.webproject.aibalkanforumproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {

    List<Question> findAllByTitleLike(String title);
    List<Question> findAllByDescriptionContaining(String keyWord);
    List<Question> findQuestionsByUser(User user);
    @Query(value = "SELECT * " +
            "FROM QUESTION q " +
            "WHERE q.title LIKE %:titleAndDesc% OR q.description LIKE %:titleAndDesc%",
            nativeQuery = true)
    List<Question> findQuestionsByTitleLikeOrDescriptionLike(String titleAndDesc);
    @Query(value = "SELECT * " +
            "FROM QUESTION q " +
            "WHERE q.id = :id AND " +
            "(q.title LIKE %:titleAndDesc% OR q.description LIKE %:titleAndDesc%)",
            nativeQuery = true)
    Question findQuestionByIdAndTitleAndDescriptionLike(Long id,String titleAndDesc);
}
