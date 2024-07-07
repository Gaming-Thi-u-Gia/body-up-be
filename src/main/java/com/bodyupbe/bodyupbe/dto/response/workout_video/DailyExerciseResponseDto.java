package com.bodyupbe.bodyupbe.dto.response.workout_video;

import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramSlimResponse;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class DailyExerciseResponseDto {
    private Integer id;
    private String day;
    Set<DailyVideoResponseDto> dailyVideos;
}
