package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.workout_video.DailyExercise;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyExerciseRepository extends JpaRepository<DailyExercise,Integer> {
}
