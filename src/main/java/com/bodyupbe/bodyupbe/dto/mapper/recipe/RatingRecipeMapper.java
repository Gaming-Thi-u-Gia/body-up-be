package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RatingRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RatingRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RatingRecipeMapper {
    RatingRecipe toRatingRecipe(RatingRecipeRequestDto ratingRecipeRequestDto);
    List<RatingRecipeResponseSlimDto> toListRatingRecipeSlimAndRecipeSlimUserSlimResponseDto(List<RatingRecipe> ratingRecipes);
    RatingRecipeResponseSlimDto toRatingRecipeResponseSlimDto(RatingRecipe ratingRecipe);
}
