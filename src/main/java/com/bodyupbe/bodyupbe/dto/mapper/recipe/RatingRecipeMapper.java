package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RatingRecipeDto;
import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RatingRecipeMapper {
    RatingRecipe toRatingRecipe(RatingRecipeDto ratingRecipeDto);
    RatingRecipeDto toRatingRecipeDto(RatingRecipe ratingRecipe);
}
