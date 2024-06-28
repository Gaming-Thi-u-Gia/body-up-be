package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeCategoryRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategorySlimAndSetRecipeSlimResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategoryResponseSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategoryTableResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.RecipeCategory;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RecipeCategoryMapper {
    RecipeCategorySlimAndSetRecipeSlimResponseDto toRecipeCategorySlimAndSetRecipeSlimResponseDto(RecipeCategory recipeCategory);
    RecipeCategory toRecipeCategory(RecipeCategoryRequestDto request);
    Set<RecipeCategorySlimAndSetRecipeSlimResponseDto> toListRecipeCategorySlimAndSetRecipeSlimResponseDto(Set<RecipeCategory> recipeCategories);
    Set<RecipeCategoryResponseSlimDto> toSetRecipeCategoryResponseSlimDto(Set<RecipeCategory> recipeCategories);
    Set<RecipeCategorySlimAndSetRecipeSlimResponseDto> toSetRecipeCategorySlimAndSetRecipeSlimResponseDto(List<RecipeCategory> recipeCategories);
    Set<RecipeCategoryTableResponseDto> toSetRecipeCategoryTableResponseDto(List<RecipeCategory> listRecipeCategory);
    RecipeCategoryResponseSlimDto toRecipeCategoryResponseSlimDto(RecipeCategory recipeCategory);
}
