package com.bodyupbe.bodyupbe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipesCollectionDto {
    Integer id;
    RecipeDto recipeDto;
    TopicDto topicDto;
}
