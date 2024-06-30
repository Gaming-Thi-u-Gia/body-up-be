package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
    @Query("SELECT NEW com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto(u.id, u.userName, u.firstName, u.lastName, u.email, u.avatar, u.bio, u.role, u.createAt) FROM User u")
    Page<UserSlimResponseDto> findAllUserSlim(Pageable pageable);
}