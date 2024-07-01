package com.bodyupbe.bodyupbe.repository;


import com.bodyupbe.bodyupbe.model.workout_video.DailyExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DailyExerciseRepository extends JpaRepository<DailyExercise,Integer> {
    @Query("SELECT de FROM DailyExercise de JOIN FETCH de.dailyVideos dv JOIN FETCH dv.video WHERE de.workoutProgram.id = :workoutProgramId")
    List<DailyExercise> findByWorkoutProgramIdWithVideos(@Param("workoutProgramId") Integer workoutProgramId);

    @Query("SELECT de FROM DailyExercise de JOIN FETCH de.workoutProgram wp JOIN FETCH de.dailyVideos dv JOIN FETCH dv.video")
    List<DailyExercise> findAllWithDetails();

}
