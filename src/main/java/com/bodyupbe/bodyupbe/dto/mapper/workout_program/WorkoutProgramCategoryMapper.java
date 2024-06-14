package com.bodyupbe.bodyupbe.dto.mapper.workout_program;

import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramCategoryResponseDto;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgramCategory;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface WorkoutProgramCategoryMapper {
    WorkoutProgramCategory toWorkoutProgramCategory(WorkoutProgramCategory workoutProgramCategory);

    List<WorkoutProgramCategoryResponseDto> toListWorkoutProgramCategoryResponseDto(List<WorkoutProgramCategory> workoutProgramCategories);

    WorkoutProgramCategoryResponseDto toWorkoutProgramCategorySlimResponse(WorkoutProgramCategory workoutProgramCategory);

    Set<WorkoutProgramCategoryResponseDto> toSetWorkoutProgramCategory(Set<WorkoutProgramCategory> workoutProgramCategories);
}
