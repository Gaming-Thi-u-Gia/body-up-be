package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Integer> {
    List<Topic> findByTopic(String topic);
}
