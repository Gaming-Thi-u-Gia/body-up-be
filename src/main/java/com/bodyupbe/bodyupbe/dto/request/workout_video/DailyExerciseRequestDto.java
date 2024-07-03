package com.bodyupbe.bodyupbe.dto.request.workout_video;

import com.bodyupbe.bodyupbe.dto.request.recipe.DailyRecipeRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyExerciseRequestDto {
    int id;
    String day;
    Set<DailyVideoRequestDto> dailyVideos;
    Set<DailyRecipeRequestDto> dailyRecipes;
}
