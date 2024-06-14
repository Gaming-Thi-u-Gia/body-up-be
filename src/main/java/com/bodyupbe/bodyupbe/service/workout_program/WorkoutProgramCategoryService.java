package com.bodyupbe.bodyupbe.service.workout_program;

import com.bodyupbe.bodyupbe.dto.mapper.workout_program.WorkoutProgramCategoryMapper;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramCategoryResponseDto;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgramCategory;
import com.bodyupbe.bodyupbe.repository.WorkoutProgramCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkoutProgramCategoryService {
    WorkoutProgramCategoryMapper workoutProgramCategoryMapper;
    WorkoutProgramCategoryRepository workoutProgramCategoryRepository;

    public List<WorkoutProgramCategoryResponseDto> getAllWorkoutProgramCategory() {
        List<WorkoutProgramCategory> workoutProgramCategories = workoutProgramCategoryRepository.findAll();
        return workoutProgramCategoryMapper.toListWorkoutProgramCategoryResponseDto(workoutProgramCategories);
    }

    public WorkoutProgramCategoryResponseDto getWorkoutProgramCategoryById(int id) {
        WorkoutProgramCategory workoutProgramCategories = workoutProgramCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Workout program category not found"));
        return workoutProgramCategoryMapper.toWorkoutProgramCategorySlimResponse(workoutProgramCategories);
    }
}
