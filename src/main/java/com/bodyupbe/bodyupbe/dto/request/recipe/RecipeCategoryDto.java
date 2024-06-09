package com.bodyupbe.bodyupbe.dto.request.recipe;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeCategoryDto {
    Integer id;
    String name;
    String type;
    Set<RecipeDto> recipes;
}
