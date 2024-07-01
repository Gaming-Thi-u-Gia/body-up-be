package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.response.recipe.DailyRecipesResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.DailyRecipe;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DailyRecipesMapper {

    Set<DailyRecipesResponseDto> dailyRecipeToDailyRecipesResponseDto(Set<DailyRecipe> dailyRecipe);
}
