package com.bodyupbe.bodyupbe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDailyChallengeDto {
    int id;
    String status;
    UserDto userDto;
    DailyExerciseDto dailyExerciseDto;
}
