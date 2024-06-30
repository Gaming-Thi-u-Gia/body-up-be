package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgram,Integer> {
    @Query(value = "SELECT wp FROM WorkoutProgram wp ORDER BY wp.id DESC LIMIT 10")
    List<WorkoutProgram> findTop10WorkoutPrograms();
}
