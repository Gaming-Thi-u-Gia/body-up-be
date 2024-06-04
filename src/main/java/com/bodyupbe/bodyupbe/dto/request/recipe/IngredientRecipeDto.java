package com.bodyupbe.bodyupbe.dto.request.recipe;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class IngredientRecipeDto {
    Integer id;
    String amount;
    String name;
    RecipeDto recipeDto;
}
