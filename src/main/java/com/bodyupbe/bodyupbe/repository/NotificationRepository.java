package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramNotificationResponseDto;
import com.bodyupbe.bodyupbe.model.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface NotificationRepository extends JpaRepository<Notification, Integer>{
    @Modifying
    @Transactional
    @Query("DELETE FROM Notification n WHERE n.createdAt < :cutoff")
    void deleteOldNotifications(Date cutoff);
    @Query("select new com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramNotificationResponseDto(wp.name,wp.img) from Notification n join n.workoutProgram wp where n.id = :id")
    WorkoutProgramNotificationResponseDto findWorkoutProgramNotificationById(@Param("id") int id);
    void deleteByWorkoutProgramId(int id);
}
