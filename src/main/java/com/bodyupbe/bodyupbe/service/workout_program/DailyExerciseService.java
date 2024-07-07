package com.bodyupbe.bodyupbe.service.workout_program;

import com.bodyupbe.bodyupbe.dto.mapper.workout_program.DailyExerciseMapper;
import com.bodyupbe.bodyupbe.dto.response.workout_video.DailyExerciseResponseDto;
import com.bodyupbe.bodyupbe.model.workout_video.DailyExercise;
import com.bodyupbe.bodyupbe.repository.DailyExerciseRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyExerciseService {
    DailyExerciseRepository dailyExerciseRepository;
    DailyExerciseMapper dailyExerciseMapper;


    public List<DailyExerciseResponseDto> getDailyExercisesByWorkoutProgramId(int workoutProgramId) {
        List<DailyExercise> exercises = dailyExerciseRepository.findByWorkoutProgramIdWithVideos(workoutProgramId);
        return dailyExerciseMapper.toDailyExerciseResponseDtoList1(new HashSet<>(exercises));
    }
    public Set<DailyExerciseResponseDto> getAllDailyExercises() {
        Set<DailyExercise> exercises = new HashSet<>(dailyExerciseRepository.findAllWithDetails());
        return dailyExerciseMapper.toDailyExerciseResponseDtoList(exercises);
    }

}
