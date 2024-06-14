package com.bodyupbe.bodyupbe.dto.response.recipe;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeCategorySlimAndSetRecipeSlimResponseDto {
    Integer id;
    String name;
    String type;
    String img;
    Set<RecipeSlimResponseDto> recipes;
}
