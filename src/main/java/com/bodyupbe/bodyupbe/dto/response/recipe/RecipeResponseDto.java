package com.bodyupbe.bodyupbe.dto.response.recipe;

import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeResponseDto {
    Integer id;
    String name;
    String detail;
    double avgStar;
    int prepTime;
    int cookTime;
    String img;
    String cookingInstruction;
    Date createAt;
    Set<RatingRecipeResponseSlimDto> ratingRecipes;
    Set<IngredientRecipeAndSetRecipeSlimResponseDto> ingredientRecipes;
    Set<OtherImageRecipeAndRecipeSlimResponseDto> otherImageRecipes;
    Set<NoteRecipeAndSetRecipeResponseDto> noteRecipes;
    Set<UserSlimResponseDto> bookmarkUsers;
    Set<TopicRecipeResponseSlimDto> topics;
    Set<RecipeCategoryResponseSlimDto> recipeCategories;
}
