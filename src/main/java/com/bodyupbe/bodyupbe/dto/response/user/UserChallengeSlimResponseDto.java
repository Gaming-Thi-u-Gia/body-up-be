package com.bodyupbe.bodyupbe.dto.response.user;


import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramSlimResponseDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserChallengeSlimResponseDto {
    int id;
    String status;
    WorkoutProgramSlimResponseDto workoutProgram;
}
