package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgramCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutProgramCategoryRepository extends JpaRepository<WorkoutProgramCategory, Integer> {
}
