package com.bodyupbe.bodyupbe.dto.response.workout_program;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
public class WorkoutProgramNotificationResponseDto {
    String name;
    String img;
}
