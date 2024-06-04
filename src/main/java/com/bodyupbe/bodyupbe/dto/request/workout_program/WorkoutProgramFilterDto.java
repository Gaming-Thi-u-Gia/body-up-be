package com.bodyupbe.bodyupbe.dto.request.workout_program;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutProgramFilterDto {
    int id;
    WorkoutProgramDto workoutProgramDto;
    WorkoutProgramCategoryDto workoutProgramCategoryDto;
}
