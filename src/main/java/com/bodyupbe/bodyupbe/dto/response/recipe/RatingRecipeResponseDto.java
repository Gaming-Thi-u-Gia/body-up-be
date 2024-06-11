package com.bodyupbe.bodyupbe.dto.response.recipe;

import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingRecipeResponseDto {
    int id;
    int star;
    RecipeSlimResponseDto recipe;
    UserSlimResponseDto user;
}
