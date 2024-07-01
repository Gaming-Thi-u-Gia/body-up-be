package com.bodyupbe.bodyupbe.dto.response.workout_program;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.request.workout_program.WorkoutProgramCategoryRequestDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.DailyExerciseDto;
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
    String img;
    String banner;
    Date releaseDate;
    Set<TopicDto> programTopics;
    Set<DailyExerciseDto> dailyExercises;
    Set<WorkoutProgramCategorySlimResponseDto> workoutProgramCategories;
}
