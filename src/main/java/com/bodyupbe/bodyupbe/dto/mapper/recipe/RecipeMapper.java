package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.BookmarkRecipeResponseBookmarkSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeSlimResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.user.User;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface RecipeMapper {
    Recipe toEntity(RecipeRequestDto recipeRequestDto);
    RecipeSlimResponseDto toSlimResponseDto(Recipe recipe);
    RecipeResponseDto toResponseDto(Recipe recipe);
    List<RecipeResponseDto> toResponseDtoList(List<Recipe> recipes);
    Set<RecipeResponseDto> toResponseDtoSet(Set<Recipe> recipes);
    Set<RecipeResponseDto> toSetRecipeResponseDto(List<Recipe> recipes);
}
