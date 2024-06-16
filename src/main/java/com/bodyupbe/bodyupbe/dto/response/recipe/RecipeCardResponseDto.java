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
public class RecipeCardResponseDto {
    Integer id;
    String name;
    double avgStar;
    String img;
    boolean bookmarked;
    int currentRating;
    Date createAt;
    Set<RecipeCategoryResponseSlimDto> recipeCategories;
}
