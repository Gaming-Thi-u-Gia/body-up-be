package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.OtherImageRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.OtherImageRecipeAndRecipeSlimResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.OtherImageRecipe;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface OtherImageRecipeMapper{
    OtherImageRecipe toOtherImageRecipe(OtherImageRecipeRequestDto otherImageRecipeRequestDto);
    Set<OtherImageRecipeAndRecipeSlimResponseDto> toSetOtherImageRecipeAndRecipeSlimResponseDto(List<OtherImageRecipe> otherImageRecipes);
    OtherImageRecipeAndRecipeSlimResponseDto toOtherImageRecipeAndRecipeSlimResponseDto(OtherImageRecipe otherImageRecipe);
}
