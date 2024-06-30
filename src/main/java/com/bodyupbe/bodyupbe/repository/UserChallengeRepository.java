package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.user.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserChallengeRepository extends JpaRepository<UserChallenge,Integer> {

    UserChallenge findByWorkoutProgramIdAndUserId(Integer workoutProgramId, Integer userId);
}
