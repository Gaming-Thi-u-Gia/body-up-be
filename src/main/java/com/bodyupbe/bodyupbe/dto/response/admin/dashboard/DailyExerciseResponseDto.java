package com.bodyupbe.bodyupbe.dto.response.admin.dashboard;

import com.bodyupbe.bodyupbe.dto.request.recipe.DailyRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.DailyVideoRequestDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.DailyVideoResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyExerciseResponseDto {
    int id;
    String day;
    Set<DailyVideoResponseDto> dailyVideos;
    Set<DailyRecipeRequestDto> dailyRecipes;
}
