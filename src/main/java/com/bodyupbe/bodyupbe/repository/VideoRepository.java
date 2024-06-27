package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.workout_video.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VideoRepository extends JpaRepository<Video,Integer> {
    @Query(value = "SELECT COUNT(*) FROM Video v")
    int countVideo();
}
