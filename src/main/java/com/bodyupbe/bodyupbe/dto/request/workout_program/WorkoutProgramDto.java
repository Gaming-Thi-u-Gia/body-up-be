package com.bodyupbe.bodyupbe.dto.request.workout_program;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.DailyExerciseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserChallengeResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
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
    Set<DailyExerciseDto> dailyExercise;
    Set<UserChallengeResponseDto> userChallenge;
    Set<TopicDto> programTopics;
    Set<WorkoutProgramCategoryDto> workoutProgramCategories;
}
