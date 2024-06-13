package com.bodyupbe.bodyupbe.dto.response.recipe;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtherImageRecipeAndRecipeSlimResponseDto {
    Integer id;
    String img;
    RecipeSlimResponseDto recipe;
}
