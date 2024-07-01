package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.user.UserChallenge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface UserChallengeRepository extends JpaRepository<UserChallenge,Integer> {
    @Query("SELECT COUNT(u) FROM UserChallenge u WHERE u.status = 'uncompleted'")
    int countUserChallengeUncompleted();
    @Query("SELECT COUNT(u) FROM UserChallenge u WHERE u.status = 'completed'")
    int countUserChallengeComplete();
    //findUserChallengeCompletedCountByMonthSince
//    @Query("SELECT TO_CHAR(uc.createAt, 'YYYY-MM') AS month, COUNT(u) AS count " +
//            "FROM UserChallenge uc " +
//            "WHERE uc.createAt >= :startDate and uc.status='completed' " +
//            "GROUP BY TO_CHAR(uc.createAt, 'YYYY-MM')")
//    List<Object[]> findUserChallengeCompletedCountByMonthSince(Date startDate);@Query("SELECT TO_CHAR(uc.createAt, 'YYYY-MM') AS month, COUNT(u) AS count " +
//            "FROM UserChallenge uc " +
//            "WHERE uc.createAt >= :startDate and uc.status='uncompleted' " +
//            "GROUP BY TO_CHAR(uc.createAt, 'YYYY-MM')")
//    List<Object[]> findUserChallengeUncompletedCountByMonthSince(Date startDate);

    UserChallenge findByWorkoutProgramIdAndUserId(Integer workoutProgramId, Integer userId);
}

