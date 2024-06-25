
package com.bodyupbe.bodyupbe.dto.mapper.workout_program;

import com.bodyupbe.bodyupbe.dto.request.workout_program.WorkoutProgramRequestDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramSlimResponse;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface WorkoutProgramMapper {
    WorkoutProgram toWorkoutProgram(WorkoutProgramRequestDto workoutProgramRequestDto);
    WorkoutProgramResponseDto toWorkoutProgramResponseDto(WorkoutProgram workoutProgram);

    List<WorkoutProgramResponseDto> toListWorkoutProgramResponseDto(List<WorkoutProgram> workoutPrograms);

    Set<WorkoutProgramResponseDto> toSetWorkoutProgram(Set<WorkoutProgram> workoutPrograms);

    Set<WorkoutProgramSlimResponse> toSetWorkoutProgramSlim(List<WorkoutProgram> workoutPrograms);
}

