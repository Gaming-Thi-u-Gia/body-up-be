package com.bodyupbe.bodyupbe.dto.response.recipe;

import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicRecipeResponseDto {
    Integer id;
    String topic;
    String name;
    Set<RecipeResponseTopicRecipeDto> recipes;
}
