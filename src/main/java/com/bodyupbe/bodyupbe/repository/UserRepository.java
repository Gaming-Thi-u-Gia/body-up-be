package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.dto.response.NotificationResponseDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.TopUserChallengeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import com.bodyupbe.bodyupbe.model.Notification;
import com.bodyupbe.bodyupbe.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.bodyupbe.bodyupbe.model.user.UserProgressPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
    @Query(value = "SELECT COUNT(*) FROM User u")
    int countUser();
    @Query("SELECT TO_CHAR(u.createAt, 'YYYY-MM') AS month, COUNT(u) AS count " +
            "FROM User u " +
            "WHERE u.createAt >= :startDate " +
            "GROUP BY TO_CHAR(u.createAt, 'YYYY-MM')")
    List<Object[]> findUserCountByMonthSince(Date startDate);
    User findByUserName2(String userName2);
    @Query("SELECT NEW com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto(u.id, u.userName, u.firstName, u.lastName, u.email, u.avatar, u.bio, u.role, u.createAt) " +
            "FROM User u " +
            "WHERE LOWER(u.userName) LIKE LOWER(CONCAT('%', :userName, '%')) " +
            "ORDER BY u.createAt DESC")
    Page<UserSlimResponseDto> findAllUserSlim(Pageable pageable, @Param("userName") String userName);
    @Query("SELECT new com.bodyupbe.bodyupbe.dto.response.admin.dashboard.TopUserChallengeResponseDto(" +
            "u.id, u.userName, u.firstName, u.lastName, u.email, COUNT(uc)) " +
            "FROM User u JOIN u.userChallenges uc " +
            "WHERE uc.status = 'completed' " +
            "GROUP BY u.id, u.userName, u.firstName, u.lastName, u.email " +
            "ORDER BY COUNT(uc) DESC")
    List<TopUserChallengeResponseDto> findTop3UsersWithMostCompletedChallenges();
    @Query("SELECT NEW com.bodyupbe.bodyupbe.dto.response.NotificationResponseDto(uf.id,uf.message,uf.createdAt,null )  FROM User u JOIN u.notifications uf WHERE u.id = :id")
    Page<NotificationResponseDto> findAllNotificationById(Pageable pageable, int id);
}