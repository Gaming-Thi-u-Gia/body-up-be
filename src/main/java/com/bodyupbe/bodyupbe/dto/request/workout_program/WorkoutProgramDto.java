package com.bodyupbe.bodyupbe.dto.request.workout_program;

import com.bodyupbe.bodyupbe.dto.request.workout_video.DailyExerciseDto;
import com.bodyupbe.bodyupbe.dto.request.user.UserChallengeDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutProgramDto {
    int id;
    String name;
    String type;
    String equipment;
    String detail;
    String day;
    String time;
    String year;
    Date releaseDate;
    Set<DailyExerciseDto> dailyExerciseDtos;
    Set<WorkoutProgramFilterDto> workoutProgramFilterDtos;
    Set<WorkoutProgramCollectionDto> workoutProgramCollectionDtos;
    Set<UserChallengeDto> userChallengeDtos;
}
