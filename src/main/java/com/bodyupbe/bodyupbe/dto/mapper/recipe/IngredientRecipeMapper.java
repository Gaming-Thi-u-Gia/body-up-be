package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.IngredientRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.IngredientRecipeAndSetRecipeSlimResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.IngredientRecipe;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface IngredientRecipeMapper {
    IngredientRecipeAndSetRecipeSlimResponseDto toIngredientRecipeAndSetRecipeSlimResponseDto(IngredientRecipe ingredientRecipe);
    IngredientRecipe toIngredientRecipe(IngredientRecipeRequestDto request);
    Set<IngredientRecipeAndSetRecipeSlimResponseDto> toSetIngredientRecipeAndSetRecipeSlimResponseDto(List<IngredientRecipe> ingredientRecipes);
}
