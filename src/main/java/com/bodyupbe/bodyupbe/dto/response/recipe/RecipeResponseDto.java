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
    Set<RatingRecipeSlimAndRecipeSlimUserSlimResponseDto> ratingRecipes;
    Set<IngredientRecipeAndSetRecipeSlimResponseDto> ingredientRecipes;
    Set<OtherImageRecipeAndRecipeSlimResponseDto> otherImageRecipes;
    Set<NoteRecipeAndSetRecipeResponseDto> noteRecipes;
    Set<UserSlimResponseDto> bookmarkUsers;
    Set<TopicRecipeResponseSlimDto> topics;
    Set<RecipeCategoryResponseSlimDto> recipeCategories;
}
