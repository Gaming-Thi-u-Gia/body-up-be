package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface TopicRepository extends JpaRepository<Topic,Integer> {
    Set<Topic> findTopicsByTopic(String topic);

    Set<Topic> findTopicsById(int id);
}
