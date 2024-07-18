package com.bodyupbe.bodyupbe.dto.response.workout_program;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.response.FeedbackWorkoutResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserChallengeSlimResponseDto;
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
    String img;
    String banner;
    Double averageStar;
    Date releaseDate;
    Set<TopicDto> programTopics;
    Set<UserChallengeSlimResponseDto> userChallenges;
    Set<DailyExerciseUserSlimResponseDto> dailyExercises;
    Set<WorkoutProgramCategorySlimResponseDto> workoutProgramCategories;
    Set<FeedbackWorkoutResponseDto> feedbackWorkouts;
}
