
package com.bodyupbe.bodyupbe.repository;


import com.bodyupbe.bodyupbe.model.workout_video.DailyVideo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface DailyVideoRepository extends JpaRepository<DailyVideo,Integer> {
    @Query("SELECT dv FROM DailyVideo dv " +
            "JOIN dv.dailyExercise de " +
            "JOIN de.userDailyChallenges udc " +
            "JOIN udc.user u " +
            "JOIN de.workoutProgram wp " +
            "WHERE u.id = :userId AND de.day = :day AND wp.id = :workoutProgramId")
    Set<DailyVideo> findAllByUserIdAndDayAndWorkoutProgramId(@Param("userId") Integer userId,
                                                             @Param("day") String day,
                                                             @Param("workoutProgramId") Integer workoutProgramId);

}
