package com.bodyupbe.bodyupbe.dto.request.recipe;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeRequestDto {
    String name;
    String detail;
    int prepTime;
    int cookTime;
    String img;
    String cookingInstruction;
    Set<IngredientRecipeRequestDto> ingredientRecipes;
    Set<OtherImageRecipeRequestDto> otherImageRecipes;
    Set<NoteRecipeRequestDto> noteRecipes;
    Set<TopicDto> recipeTopics;
    Set<RecipeCategoryRequestDto> recipeCategories;
}
