package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.workout_video.VideoCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoCategoryRepository extends JpaRepository<VideoCategory,Integer> {
}
