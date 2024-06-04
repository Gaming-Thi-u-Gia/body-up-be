package com.bodyupbe.bodyupbe.dto.request.workout_program;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutProgramCollectionDto {
    int id;
    WorkoutProgramDto workoutProgramDto;
    TopicDto topicDto;
}
