package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.*;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe toRecipe(RecipeRequestDto recipeRequestDto);
    RecipeDetailResponseDto toRecipeDetailResponseDto(Recipe recipe);
    RecipeSlimResponseDto toRecipeSlimResponseDto(Recipe recipe);
    RecipeResponseDto toRecipeResponseDto(Recipe recipe);
    Set<RecipeResponseDto> toSetRecipeResponseDto(List<Recipe> recipes);
    Set<RecipeResponseDto> toSetRecipeResponseDto(Set<Recipe> recipes);
    Set<RecipeSlimAndSetRecipeCategorySlimResponseDto> toSetRecipeSlimAndSetRecipeCategorySlimResponseDto(Set<Recipe> recipes);
    Set<RecipeLatestResponseDto> toSetRecipeLatestResponseDto(Set<Recipe> recipes);
    Set<RecipeCardResponseDto> toSetRecipeCardResponseDto(Set<Recipe> recipes);
    Set<RecipeCardResponseDto> toSetRecipeCardResponseDto(List<Recipe> recipes);
}
