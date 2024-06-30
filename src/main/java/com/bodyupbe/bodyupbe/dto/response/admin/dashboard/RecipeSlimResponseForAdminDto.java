package com.bodyupbe.bodyupbe.dto.response.admin.dashboard;

import com.bodyupbe.bodyupbe.dto.response.recipe.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeSlimResponseForAdminDto {
    Integer id;
    String name;
    String detail;
    double avgStar;
    int totalRating;
    int prepTime;
    int cookTime;
    String img;
    String cookingInstruction;
    Date createdAt;
    Set<RecipeCategoryResponseSlimDto> recipeCategories;
    Set<NoteRecipeAndSetRecipeResponseDto> noteRecipes;
    Set<IngredientRecipeSlimResponseDto> ingredientRecipes;
    Set<OtherImageRecipeAndRecipeSlimResponseDto> otherImageRecipes;
    Set<TopicRecipeResponseSlimDto> recipeTopics;
}
