package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

//Made By Filip Stavrov

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    //List<Event> findByHostedBy(String hostedBy);
    //List<Event> findByDate(LocalDateTime date);
}
