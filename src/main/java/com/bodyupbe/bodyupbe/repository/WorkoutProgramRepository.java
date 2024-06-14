package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgram,Integer> {
}
