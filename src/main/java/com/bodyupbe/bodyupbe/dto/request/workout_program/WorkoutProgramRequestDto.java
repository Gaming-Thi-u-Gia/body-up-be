package com.bodyupbe.bodyupbe.dto.request.workout_program;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.DailyExerciseDto;
import com.bodyupbe.bodyupbe.dto.request.user.UserChallengeDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutProgramRequestDto {
    int id;
    String name;
    String type;
    String equipment;
    String detail;
    String day;
    String time;
    String year;
    String img;
    String banner;
    Date releaseDate;
    Set<DailyExerciseDto> dailyExerciseDtos;
    Set<UserChallengeDto> userChallengeDtos;
    Set<TopicDto> programTopics;
    Set<WorkoutProgramCategoryRequestDto> workoutProgramCategories;
}
