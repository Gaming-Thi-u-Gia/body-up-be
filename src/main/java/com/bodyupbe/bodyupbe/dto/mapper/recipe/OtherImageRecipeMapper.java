package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.OtherImageRecipeDto;
import com.bodyupbe.bodyupbe.model.recipe.OtherImageRecipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OtherImageRecipeMapper {
    OtherImageRecipeDto toOtherImageRecipeDto(OtherImageRecipe otherImageRecipe);
    OtherImageRecipe toOtherImageRecipe(OtherImageRecipeDto otherImageRecipeDto);
}
