package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.user.UserDailyChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserDailyChallengeRepository extends JpaRepository<UserDailyChallenge, Integer> {
    // Query to find all UserDailyChallenges for a specific user and workout program
    Set<UserDailyChallenge> findByUserIdAndDailyExercise_WorkoutProgramId(Integer userId, Integer workoutProgramId);

    @Query("SELECT udc " +
            "FROM UserDailyChallenge udc " +
            "JOIN udc.dailyExercise de " +
            "WHERE udc.user.id = :userId AND de.workoutProgram.id = :workoutProgramId")
    Set<UserDailyChallenge> findAllByUserAndWorkoutProgram(@Param("userId") Integer userId,
                                                           @Param("workoutProgramId") Integer workoutProgramId);

    @Query("SELECT udc " +
            "FROM UserDailyChallenge udc " +
            "JOIN udc.dailyExercise de " +
            "WHERE udc.status = :status AND udc.user.id = :userId " +
            "ORDER BY CAST(de.day AS INTEGER) ASC limit 1")
    UserDailyChallenge findByStatusSortedByExerciseDay(@Param("status") String status,@Param("userId") Integer userId);

    UserDailyChallenge findByDailyExerciseIdAndUserId(Integer dailyExerciseId, Integer userId);
}
