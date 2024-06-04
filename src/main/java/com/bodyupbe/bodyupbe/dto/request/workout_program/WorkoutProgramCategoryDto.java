package com.bodyupbe.bodyupbe.dto.request.workout_program;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutProgramCategoryDto {
    int id;
    Set<WorkoutProgramFilterDto> workoutProgramFilterDtos;
}
