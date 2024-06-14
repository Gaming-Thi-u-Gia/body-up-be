package com.bodyupbe.bodyupbe.dto.response.recipe;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicRecipeSlimAndSetRecipeSlimVsSetRecipeCategorySlimResponseDto {
    Integer id;
    String topic;
    String name;
    String description;
    Set<RecipeSlimAndSetRecipeCategorySlimResponseDto> recipes;
}
