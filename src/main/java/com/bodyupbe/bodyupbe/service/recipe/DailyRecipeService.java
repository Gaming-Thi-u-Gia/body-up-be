package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.DailyRecipesMapper;
import com.bodyupbe.bodyupbe.dto.response.recipe.DailyRecipesResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.DailyVideoResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.DailyRecipe;
import com.bodyupbe.bodyupbe.model.workout_video.DailyVideo;
import com.bodyupbe.bodyupbe.repository.DailyRecipeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyRecipeService {
    DailyRecipeRepository dailyRecipeRepository;
    DailyRecipesMapper dailyRecipesMapper;

    public Set<DailyRecipesResponseDto> getRecipesByDay(String day, Integer workoutProgramId) {
        Set<DailyRecipe> recipes = dailyRecipeRepository.findAllByDayAndWorkoutProgramId(day, workoutProgramId);
        return dailyRecipesMapper.dailyRecipeToDailyRecipesResponseDto(recipes);
    }
}
