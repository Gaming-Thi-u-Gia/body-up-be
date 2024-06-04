package com.bodyupbe.bodyupbe.dto.request.workout_video;

import com.bodyupbe.bodyupbe.dto.request.user.UserDailyChallengeDto;
import com.bodyupbe.bodyupbe.dto.request.workout_program.WorkoutProgramDto;
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
