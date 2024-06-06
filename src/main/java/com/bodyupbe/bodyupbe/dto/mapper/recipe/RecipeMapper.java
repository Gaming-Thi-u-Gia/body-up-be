package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeDto;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    RecipeDto toRecipeDto(Recipe recipe);
    Recipe toRecipe(RecipeDto recipeDto);
}
