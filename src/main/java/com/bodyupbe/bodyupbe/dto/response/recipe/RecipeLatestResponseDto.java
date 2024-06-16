package com.bodyupbe.bodyupbe.dto.response.recipe;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeLatestResponseDto {
    Integer id;
    String name;
    double avgStar;
    String detail;
    String img;
    int currentRating;
    boolean bookmarked;
    Set<RecipeCategoryResponseSlimDto> recipeCategories;
}
