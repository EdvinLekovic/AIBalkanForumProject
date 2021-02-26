package com.webproject.aibalkanforumproject.repository;

import com.webproject.aibalkanforumproject.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EventRepository extends CrudRepository<Event, Long> {

    @Query("from Event e where not(e.endTime < :from or e.startTime > :to)")
     List<Event> findBetween(@Param("from") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                   @Param("to") @DateTimeFormat(iso= DateTimeFormat.ISO.DATE_TIME) LocalDateTime to);

}
