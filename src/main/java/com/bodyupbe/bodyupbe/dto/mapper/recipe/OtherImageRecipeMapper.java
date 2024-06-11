package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.OtherImageRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.OtherImageRecipeResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.OtherImageRecipe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OtherImageRecipeMapper{
    OtherImageRecipe toEntity(OtherImageRecipeRequestDto otherImageRecipeRequestDto);
    List<OtherImageRecipeResponseDto> toResponseDtoList(List<OtherImageRecipe> otherImageRecipes);
    OtherImageRecipeResponseDto toResponseDto(OtherImageRecipe otherImageRecipe);
}
