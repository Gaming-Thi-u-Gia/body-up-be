package com.bodyupbe.bodyupbe.dto.response.recipe;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeSlimAndSetRecipeCategorySlimResponseDto {
    Integer id;
    String name;
    String detail;
    double avgStar;
    int prepTime;
    int cookTime;
    String img;
    String cookingInstruction;
    Date createAt;
    boolean bookmarked;
    Set<RecipeCategoryResponseSlimDto> recipeCategories;
}
