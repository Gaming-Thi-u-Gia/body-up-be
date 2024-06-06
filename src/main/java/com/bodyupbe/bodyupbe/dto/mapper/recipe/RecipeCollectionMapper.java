package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeCollectionDto;
import com.bodyupbe.bodyupbe.model.recipe.RecipeCollection;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeCollectionMapper {
    RecipeCollectionDto toRecipeCollectionDto(RecipeCollection recipeCollection);
    RecipeCollection toRecipeCollection(RecipeCollectionDto recipeCollectionDto);
}
