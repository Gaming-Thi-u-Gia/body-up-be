package com.bodyupbe.bodyupbe.dto.response.recipe;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class RecipeCardResponseDto {
    int id;
    String name;
    double avgStar;
    String img;
    boolean bookmarked;
    int currentRating;
    Date createAt;
    Set<RecipeCategoryCardResponseDto> recipeCategories;

    public RecipeCardResponseDto(int id, String name, double avgStar, String img, Date createAt) {
        this.id = id;
        this.name = name;
        this.avgStar = avgStar;
        this.img = img;
        this.createAt = createAt;
    }
}
