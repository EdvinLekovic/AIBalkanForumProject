package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {

    //List<Event> findAllByHost(String host);
    //List<Event> findAllByDate(LocalDateTime date);
    public List<Event> findByStartGreaterThanEqualAndFinishLessThanEqual(LocalDateTime start, LocalDateTime end);

}
