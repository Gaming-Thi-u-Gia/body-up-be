package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeCategoryRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategoryResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.recipe.RecipeCategory;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RecipeCategoryMapper {
    RecipeCategoryResponseDto toResponseDto(RecipeCategory recipeCategory);
    RecipeCategory toEntity(RecipeCategoryRequestDto request);
    List<RecipeCategoryResponseDto> toResponseDtoList(List<RecipeCategory> recipeCategories);

}
