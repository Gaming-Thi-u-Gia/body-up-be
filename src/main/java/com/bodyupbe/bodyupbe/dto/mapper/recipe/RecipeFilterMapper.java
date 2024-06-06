package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeDto;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeFilterDto;
import com.bodyupbe.bodyupbe.model.recipe.RecipeFilter;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeFilterMapper {
    RecipeFilter toRecipeFilter(RecipeFilterDto recipeFilterDto);
    RecipeFilterDto toRecipeFilterDto(RecipeFilter recipeFilter);
}
