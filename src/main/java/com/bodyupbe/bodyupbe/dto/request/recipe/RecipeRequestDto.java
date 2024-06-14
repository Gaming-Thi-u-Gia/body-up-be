package com.bodyupbe.bodyupbe.dto.request.recipe;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.request.user.UserRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeRequestDto {
    Integer id;
    String name;
    String detail;
    double avgStar;
    int prepTime;
    int cookTime;
    String img;
    String cookingInstruction;
    Set<RatingRecipeRequestDto> ratingRecipes;
    Set<IngredientRecipeRequestDto> ingredientRecipes;
    Set<OtherImageRecipeRequestDto> otherImageRecipes;
    Set<NoteRecipeRequestDto> noteRecipes;
    Set<UserRequestDto> bookmarkUsers;
    Set<TopicDto> recipeTopics;
    Set<RecipeCategoryRequestDto> recipeCategories;
}
