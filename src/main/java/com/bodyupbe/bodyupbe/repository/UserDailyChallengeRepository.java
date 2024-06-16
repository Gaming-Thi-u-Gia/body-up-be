package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.user.UserDailyChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface UserDailyChallengeRepository extends JpaRepository<UserDailyChallenge, Integer> {
    // Query to find all UserDailyChallenges for a specific user and workout program
    Set<UserDailyChallenge> findByUserIdAndDailyExercise_WorkoutProgramId(Integer userId, Integer workoutProgramId);
}
