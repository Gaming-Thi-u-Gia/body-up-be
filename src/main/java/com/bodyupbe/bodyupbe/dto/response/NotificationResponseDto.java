package com.bodyupbe.bodyupbe.dto.response;

import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramNotificationResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
public class NotificationResponseDto {
    int id;
    String message;
    Date createdAt;
    WorkoutProgramNotificationResponseDto workoutProgram;
}
