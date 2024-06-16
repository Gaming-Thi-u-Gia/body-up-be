package com.bodyupbe.bodyupbe.dto.mapper.daily_exercise;

import com.bodyupbe.bodyupbe.dto.response.workout_program.DailyExerciseUserResponseDto;
import com.bodyupbe.bodyupbe.model.workout_video.DailyExercise;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DailyExerciseMapper {
    Set<DailyExerciseUserResponseDto> toListDailyExerciseUserResponseDto(Set<DailyExercise> dailyExercise);
}
