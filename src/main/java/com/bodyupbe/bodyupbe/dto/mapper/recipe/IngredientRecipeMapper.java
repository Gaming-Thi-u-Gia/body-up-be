package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.IngredientRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.IngredientRecipeSlimResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.IngredientRecipe;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface IngredientRecipeMapper {
    IngredientRecipeSlimResponseDto toIngredientRecipeAndSetRecipeSlimResponseDto(IngredientRecipe ingredientRecipe);
    IngredientRecipe toIngredientRecipe(IngredientRecipeRequestDto request);
    Set<IngredientRecipeSlimResponseDto> toSetIngredientRecipeAndSetRecipeSlimResponseDto(List<IngredientRecipe> ingredientRecipes);
}
