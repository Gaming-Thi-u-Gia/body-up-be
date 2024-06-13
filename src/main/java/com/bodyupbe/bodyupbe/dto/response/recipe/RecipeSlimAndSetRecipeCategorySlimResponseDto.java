package com.bodyupbe.bodyupbe.dto.response.recipe;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeSlimAndSetRecipeCategorySlimResponseDto {
    Integer id;
    String name;
    double avgStar;
    String prepTime;
    String cookTime;
    String img;
    String cookDetail;
    Set<RecipeCategoryResponseSlimDto> recipeCategories;
}
