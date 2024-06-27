package com.bodyupbe.bodyupbe.controller.workout_program;

import com.bodyupbe.bodyupbe.dto.response.workout_video.DailyExerciseResponseDto;

import com.bodyupbe.bodyupbe.service.workout_program.DailyExerciseService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/workout-video")
@CrossOrigin
public class DailyExcerciseController {
    DailyExerciseService dailyExerciseService;

    @GetMapping("/getDailyExercisesByWorkoutProgramId")
    public ResponseEntity<List<DailyExerciseResponseDto>> getDailyExercisesByWorkoutProgramId(@RequestParam int workoutProgramId) {
        return ResponseEntity.ok(dailyExerciseService.getDailyExercisesByWorkoutProgramId(workoutProgramId));
    }

    @GetMapping("/getAllDailyExercises")
    public ResponseEntity<Set<DailyExerciseResponseDto>> getAllDailyExercises() {
        return ResponseEntity.ok(dailyExerciseService.getAllDailyExercises());
    }
}
