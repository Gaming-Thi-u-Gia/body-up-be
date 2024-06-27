package com.bodyupbe.bodyupbe.dto.mapper.workout_program;

import com.bodyupbe.bodyupbe.dto.response.workout_video.DailyExerciseResponseDto;
import com.bodyupbe.bodyupbe.model.workout_video.DailyExercise;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface DailyExerciseMapper {

    @Mapping(source = "workoutProgram", target = "workoutProgramId")
    @Mapping(source = "dailyVideos", target = "dailyVideos")

    List<DailyExerciseResponseDto> toDailyExerciseResponseDtoList1(Set<DailyExercise> dailyExercises);
    Set<DailyExerciseResponseDto> toDailyExerciseResponseDtoList(Set<DailyExercise> dailyExercises);
}


