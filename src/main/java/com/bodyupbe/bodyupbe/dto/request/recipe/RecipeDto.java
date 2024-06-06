package com.bodyupbe.bodyupbe.dto.request.recipe;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeDto {
    Integer id;
    String name;
    double avgStar;
    String prepTime;
    String cookTime;
    String img;
    String cookDetail;
    Set<RecipeCollectionDto> recipeCollectionDtos;
    Set<RatingRecipeDto> ratingRecipeDtos;
    Set<IngredientRecipeDto> ingredientRecipeDtos;
    Set<OtherImageRecipeDto> otherImageRecipeDtos;
    Set<RecipeFilterDto> recipeFilterDtos;
    Set<BookmarkRecipeDto> bookmarkRecipeDtos;
    Set<NoteRecipeDto> noteRecipeDtos;
}
