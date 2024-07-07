package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramCategoryResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramCategorySlimResponseDto;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgramCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface WorkoutProgramCategoryRepository extends JpaRepository<WorkoutProgramCategory, Integer> {

    @Query("SELECT new com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramCategorySlimResponseDto(wpc.id,wpc.name,wpc.type) from WorkoutProgramCategory wpc join wpc.workoutPrograms wp where wp.id= :workoutProgramId")
    List<WorkoutProgramCategorySlimResponseDto> findAllWorkoutProgramCategoriesByWorkoutProgramId(@Param("workoutProgramId") Integer workoutProgramId);
}
