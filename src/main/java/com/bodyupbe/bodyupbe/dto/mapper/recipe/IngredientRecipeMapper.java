package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.IngredientRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.IngredientRecipeResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.IngredientRecipe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IngredientRecipeMapper {
    IngredientRecipeResponseDto toResponseDto(IngredientRecipe ingredientRecipe);
    IngredientRecipe toEntity(IngredientRecipeRequestDto request);
    List<IngredientRecipeResponseDto> toResponseDtoList(List<IngredientRecipe> ingredientRecipes);
}
