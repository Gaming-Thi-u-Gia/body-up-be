package com.bodyupbe.bodyupbe.dto.response.user;


import com.bodyupbe.bodyupbe.dto.response.workout_program.DailyExerciseUserResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDailyChallengeResponseDto {
    int id;
    String status;
    Set<DailyExerciseUserResponseDto> dailyExercises;
}
