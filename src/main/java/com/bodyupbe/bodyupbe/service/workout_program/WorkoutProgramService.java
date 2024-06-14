package com.bodyupbe.bodyupbe.service.workout_program;

import com.bodyupbe.bodyupbe.dto.mapper.TopicMapper;
import com.bodyupbe.bodyupbe.dto.mapper.workout_program.WorkoutProgramCategoryMapper;
import com.bodyupbe.bodyupbe.dto.mapper.workout_program.WorkoutProgramMapper;
import com.bodyupbe.bodyupbe.dto.request.workout_program.WorkoutProgramRequestDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramCategoryResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramResponseDto;
import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgramCategory;
import com.bodyupbe.bodyupbe.repository.TopicRepository;
import com.bodyupbe.bodyupbe.repository.WorkoutProgramCategoryRepository;
import com.bodyupbe.bodyupbe.repository.WorkoutProgramRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class WorkoutProgramService {
    WorkoutProgramMapper workoutProgramMapper;
    WorkoutProgramCategoryMapper workoutProgramCategoryMapper;
    WorkoutProgramRepository workoutProgramRepository;
    WorkoutProgramCategoryRepository workoutProgramCategoryRepository;
    TopicRepository topicRepository;
    TopicMapper topicMapper;


    public WorkoutProgramResponseDto getWorkoutProgramById(int id) {
        WorkoutProgram workoutPrograms = workoutProgramRepository.findById(id).orElseThrow(() -> new RuntimeException("Workout program not found"));
        return workoutProgramMapper.toWorkoutProgramResponseDto(workoutPrograms);
    }

    public List<WorkoutProgramResponseDto> getAllWorkoutProgram() {
        List<WorkoutProgram> workoutPrograms = workoutProgramRepository.findAll();
        return workoutProgramMapper.toListWorkoutProgramResponseDto(workoutPrograms);
    }

    public void createWorkoutProgram(WorkoutProgramRequestDto workoutProgramRequestDto) {
        workoutProgramRepository.save(workoutProgramMapper.toWorkoutProgram(workoutProgramRequestDto));
    }

    public void deleteWorkoutProgram(int id) {
        workoutProgramRepository.deleteById(id);
    }

    public WorkoutProgramResponseDto updateWorkoutProgram(WorkoutProgramRequestDto workoutProgramRequestDto) {
        WorkoutProgram updatedWorkoutProgram = workoutProgramRepository.save(workoutProgramMapper.toWorkoutProgram(workoutProgramRequestDto));
        return workoutProgramMapper.toWorkoutProgramResponseDto(updatedWorkoutProgram);
    }

    public WorkoutProgramCategoryResponseDto getWorkoutProgramByWorkoutProgramCategory(int workoutProgramCategoryId) {
        WorkoutProgramCategory workoutProgramCategory = workoutProgramCategoryRepository.findById(workoutProgramCategoryId).orElseThrow(() -> new RuntimeException("Workout program category not found"));
        return workoutProgramCategoryMapper.toWorkoutProgramCategorySlimResponse(workoutProgramCategory);
    }

    public Set<WorkoutProgramResponseDto> getWorkoutProgramByTopic(int topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new RuntimeException("Topic not found"));
        return workoutProgramMapper.toSetWorkoutProgram(topic.getWorkoutPrograms());
    }
}
