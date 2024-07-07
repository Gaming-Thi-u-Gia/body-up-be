package com.bodyupbe.bodyupbe.dto.response.recipe;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@ToString
public class TopicRecipeSlimAndSetRecipeCardResponseDto {
    Integer id;
    String topic;
    String name;
    String description;
    Set<RecipeCardResponseDto> recipes;
}
