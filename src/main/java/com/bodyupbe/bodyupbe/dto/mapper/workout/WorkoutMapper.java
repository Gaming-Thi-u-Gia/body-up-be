package com.bodyupbe.bodyupbe.dto.mapper.workout;

import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramSlimResponseDto;

import com.bodyupbe.bodyupbe.model.user.UserChallenge;

import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface WorkoutMapper {
    List<WorkoutProgramSlimResponseDto> toListWorkoutProgramSlimResponseDto(List<WorkoutProgram> workoutPrograms);
}
