package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RatingRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RatingRecipeResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingRecipeMapper {
    RatingRecipe toEntity(RatingRecipeRequestDto ratingRecipeRequestDto);
    List<RatingRecipeResponseDto> toResponseDtoList(List<RatingRecipe> ratingRecipes);
    RatingRecipeResponseDto toResponseDto(RatingRecipe ratingRecipe);
}
