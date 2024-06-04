package com.bodyupbe.bodyupbe.dto.request.recipe;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeFilterDto {
    Integer id;
    RecipeCategoryDto recipeCategoryDto;
    RecipeDto recipeDto;
}
