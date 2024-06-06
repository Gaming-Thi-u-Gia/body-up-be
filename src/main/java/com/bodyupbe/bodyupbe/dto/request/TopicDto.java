package com.bodyupbe.bodyupbe.dto.request;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeCollectionDto;
import com.bodyupbe.bodyupbe.dto.request.workout_program.WorkoutProgramCollectionDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.WorkoutVideoCollectionDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicDto {
    Integer id;
    String topic;
    String name;
    Set<WorkoutProgramCollectionDto> workoutProgramCollectionDtos;
    Set<WorkoutVideoCollectionDto> workoutVideoCollectionDtos;
    Set<RecipeCollectionDto> recipeCollectionDtos;
}
