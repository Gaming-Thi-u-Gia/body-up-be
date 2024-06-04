package com.bodyupbe.bodyupbe.dto.request.recipe;

import com.bodyupbe.bodyupbe.dto.request.user.UserDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingRecipeDto {
    Integer id;
    int star;
    RecipeDto recipeDto;
    UserDto userDto;
}
