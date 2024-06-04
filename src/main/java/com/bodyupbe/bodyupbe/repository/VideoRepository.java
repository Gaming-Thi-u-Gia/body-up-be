package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.workout_video.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video,Integer> {
}
