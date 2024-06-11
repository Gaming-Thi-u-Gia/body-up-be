package com.bodyupbe.bodyupbe.dto.response.workout_program;

import com.bodyupbe.bodyupbe.dto.response.user.UserChallengeSlimResponseDto;
import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.user.UserChallenge;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgramCategory;
import com.bodyupbe.bodyupbe.model.workout_video.DailyExercise;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class WorkoutProgramResponseDto {
    int id;
    String name;
    String type;
    String equipment;
    String detail;
    String day;
    String time;
    String year;
    Date releaseDate;
    Set<DailyExercise> dailyExercises;
    Set<UserChallengeSlimResponseDto> userChallenges;
    Set<WorkoutProgramCategorySlimResponseDto> workoutProgramCategories;
}
