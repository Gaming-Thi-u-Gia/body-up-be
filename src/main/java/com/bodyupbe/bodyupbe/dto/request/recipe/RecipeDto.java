package com.bodyupbe.bodyupbe.dto.request.recipe;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.request.user.UserDto;
import com.bodyupbe.bodyupbe.model.recipe.RecipeCategory;
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
    Set<RatingRecipeDto> ratingRecipeDtos;
    Set<IngredientRecipeDto> ingredientRecipeDtos;
    Set<OtherImageRecipeDto> otherImageRecipeDtos;

    Set<NoteRecipeDto> noteRecipeDtos;
    Set<UserDto> bookmarkUsers;
    Set<TopicDto> recipeTopics;
    Set<RecipeCategoryDto> recipeCategories;
}
