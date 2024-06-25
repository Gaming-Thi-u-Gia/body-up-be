package com.bodyupbe.bodyupbe.dto.response.workout_program;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Builder
@Getter
@Setter
public class WorkoutCategoryTableResponse {
    String type;
    Set<WorkoutProgramCategorySlimResponseDto> workoutCategories;
}
