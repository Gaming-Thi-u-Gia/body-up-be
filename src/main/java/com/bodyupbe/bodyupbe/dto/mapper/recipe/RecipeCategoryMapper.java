package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeCategoryDto;
import com.bodyupbe.bodyupbe.model.recipe.RecipeCategory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RecipeCategoryMapper {
    RecipeCategory toRecipeCategory(RecipeCategoryDto recipeCategoryDto);
    RecipeCategoryDto toRecipeCategoryDto(RecipeCategory recipeCategory);
}
