package com.bodyupbe.bodyupbe.dto.request.recipe;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RecipeCollectionDto {
    Integer id;
    RecipeDto recipeDto;
    TopicDto topicDto;
}
