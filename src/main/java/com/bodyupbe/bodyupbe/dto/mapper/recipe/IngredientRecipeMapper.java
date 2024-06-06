package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.IngredientRecipeDto;
import com.bodyupbe.bodyupbe.model.recipe.IngredientRecipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IngredientRecipeMapper {
    IngredientRecipe toIngredientRecipe(IngredientRecipeDto ingredientRecipeDto);
    IngredientRecipeDto toIngredientRecipeDto(IngredientRecipe ingredientRecipe);
}
