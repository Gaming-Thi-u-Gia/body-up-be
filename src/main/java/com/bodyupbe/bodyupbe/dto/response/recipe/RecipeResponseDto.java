package com.bodyupbe.bodyupbe.dto.response.recipe;

import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeResponseDto {
    Integer id;
    String name;
    double avgStar;
    String prepTime;
    String cookTime;
    String img;
    String cookDetail;
    Set<RatingRecipeResponseDto> ratingRecipes;
    Set<IngredientRecipeResponseDto> ingredientRecipes;
    Set<OtherImageRecipeResponseDto> otherImageRecipes;
    Set<NoteRecipeResponseDto> noteRecipes;
    Set<UserSlimResponseDto> bookmarkUsers;
    Set<TopicRecipeResponseSlimDto> topics;
    Set<RecipeCategoryResponseSlimDto> recipeCategories;
}
