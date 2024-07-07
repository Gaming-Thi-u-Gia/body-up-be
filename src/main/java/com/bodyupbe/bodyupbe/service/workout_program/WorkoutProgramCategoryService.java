package com.bodyupbe.bodyupbe.service.workout_program;

import com.bodyupbe.bodyupbe.dto.mapper.workout_program.WorkoutProgramCategoryMapper;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutCategoryTableResponse;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramCategoryResponseDto;

import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramCategorySlimResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramSlimResponse;
import com.bodyupbe.bodyupbe.dto.response.workout_video.ObjectVideo.ObjectVideoSetResponse;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoSlimResponseDto;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgramCategory;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.bodyupbe.bodyupbe.repository.WorkoutProgramCategoryRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

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

    public Set<WorkoutCategoryTableResponse> getAllWorkoutCategoriesWithEachType() {
        List<WorkoutProgramCategory> categories = workoutProgramCategoryRepository.findAll();
        List<WorkoutProgramCategorySlimResponseDto> categoryDtoList = categories.stream()
                .map(category -> workoutProgramCategoryMapper.toListWorkoutProgramCategorySlimResponseDto(category))
                .collect(Collectors.toList());

        Map<String, Set<WorkoutProgramCategorySlimResponseDto>> groupedByType = categoryDtoList.stream()
                .collect(Collectors.groupingBy(
                        WorkoutProgramCategorySlimResponseDto::getType,
                        Collectors.toSet()
                ));

        Set<WorkoutCategoryTableResponse> responseDtoSet = groupedByType.entrySet().stream()
                .map(entry -> WorkoutCategoryTableResponse.builder()
                        .type(entry.getKey())
                        .workoutCategories(entry.getValue())
                        .build())
                .collect(Collectors.toSet());

        return responseDtoSet;
    }


}
