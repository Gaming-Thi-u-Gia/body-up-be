package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import java.util.List;
import java.util.Set;

public interface TopicRepository extends JpaRepository<Topic,Integer> {
    Page<Topic> findByTopic(String topic, Pageable pageable);
    List<Topic> findByTopic(String topic);
    Set<Topic> findTopicsByTopic(String topic);

    Set<Topic> findTopicsById(int id);

    @Query("SELECT new com.bodyupbe.bodyupbe.dto.request.TopicDto(t.id,t.name) FROM Topic t join t.workoutPrograms w where w.id = :id")
    List<TopicDto> findAllTopicsByWorkoutProgramId(@Param("id") int id);
}
