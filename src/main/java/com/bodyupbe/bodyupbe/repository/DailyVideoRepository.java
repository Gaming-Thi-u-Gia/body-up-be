package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.workout_video.DailyVideo;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DailyVideoRepository extends JpaRepository<DailyVideo,Integer> {
}
