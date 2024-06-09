package com.bodyupbe.bodyupbe.dto.request;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeDto;
import com.bodyupbe.bodyupbe.dto.request.workout_program.WorkoutProgramDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.VideoDto;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
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


    Set<VideoDto> videos;
    Set<WorkoutProgramDto> workoutPrograms;
    Set<RecipeDto> recipes;
}
