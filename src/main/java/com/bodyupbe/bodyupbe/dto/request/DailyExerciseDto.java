package com.bodyupbe.bodyupbe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyExerciseDto {
    Integer id;
    WorkoutProgramDto workoutProgramDto;
    Set<DailyVideoDto> dailyViveoDtos;
    Set<UserDailyChallengeDto> userDailyChallengeDtos;
}
