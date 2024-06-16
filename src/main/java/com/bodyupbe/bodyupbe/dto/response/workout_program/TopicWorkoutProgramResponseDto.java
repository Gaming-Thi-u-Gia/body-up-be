package com.bodyupbe.bodyupbe.dto.response.workout_program;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicWorkoutProgramResponseDto {
    Integer id;
    String topic;
    String name;
    String description;

    Set<WorkoutProgramSlimResponse> workoutPrograms;
}
