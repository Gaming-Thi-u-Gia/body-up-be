package com.bodyupbe.bodyupbe.dto.response.recipe;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class DailyExerciseRecipesResponseDto {
    private Integer id;
    private String day;
    private Set<DailyRecipesResponseDto> dailyRecipes;
}
