package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeSlimResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe toRecipe(RecipeRequestDto recipeRequestDto);
    RecipeSlimResponseDto toRecipeSlimResponseDto(Recipe recipe);
    RecipeResponseDto toRecipeResponseDto(Recipe recipe);
    Set<RecipeResponseDto> toSetRecipeResponseDto(List<Recipe> recipes);
    Set<RecipeResponseDto> toSetRecipeResponseDto(Set<Recipe> recipes);
}
