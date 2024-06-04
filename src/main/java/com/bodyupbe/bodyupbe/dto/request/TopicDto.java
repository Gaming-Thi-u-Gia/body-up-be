package com.bodyupbe.bodyupbe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicDto {
    Integer id;
    String topic;
    String name;
    Set<WorkoutProgramCollectionDto> workoutProgramCollectionDtos;
    Set<WorkoutVideoCollectionDto> workoutVideoCollectionDtos;
    Set<RecipesCollectionDto> recipesCollectionDtos;
}
