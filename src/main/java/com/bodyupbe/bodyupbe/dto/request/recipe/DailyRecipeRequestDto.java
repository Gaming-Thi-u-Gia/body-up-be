package com.bodyupbe.bodyupbe.dto.request.recipe;

import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.RecipeSelectForAdminResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCardResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyRecipeRequestDto {
    int id;
    RecipeSelectForAdminResponseDto recipe;
    String part;
}
