package com.bodyupbe.bodyupbe.dto.response.recipe;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class DailyRecipesResponseDto {
    int id;
    String part;
    RecipeSlimResponseDto recipe;
}
