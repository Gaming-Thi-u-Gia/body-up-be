package com.bodyupbe.bodyupbe.dto.response.admin.dashboard;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.request.workout_program.WorkoutProgramCategoryRequestDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.DailyExerciseRequestDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramCategorySlimResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class WorkoutProgramDetailForAdminDto {
    int id;
    String name;
    String detail;
    String day;
    String equipment;
    String type;
    String time;
    String year;
    String img;
    String banner;
    Date releaseDate;
    Set<DailyExerciseResponseDto> dailyExercises;
    Set<TopicDto> programTopics;
    Set<WorkoutProgramCategoryRequestDto> workoutProgramCategories;
}
