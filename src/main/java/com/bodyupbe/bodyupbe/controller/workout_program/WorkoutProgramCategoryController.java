package com.bodyupbe.bodyupbe.controller.workout_program;

import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramCategoryResponseDto;
import com.bodyupbe.bodyupbe.service.workout_program.WorkoutProgramCategoryService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/workout-video")
@CrossOrigin
public class WorkoutProgramCategoryController {
    WorkoutProgramCategoryService workoutProgramCategoryService;

    @GetMapping("/getAllWorkoutProgramCategory")
    public ResponseEntity<List<WorkoutProgramCategoryResponseDto>> getAllWorkoutProgramCategory() {
        return ResponseEntity.ok(workoutProgramCategoryService.getAllWorkoutProgramCategory());
    }

    @GetMapping("/getWorkoutProgramCategoryById")
    public ResponseEntity<WorkoutProgramCategoryResponseDto> getWorkoutProgramCategoryById(@RequestParam int id) {
        return ResponseEntity.ok(workoutProgramCategoryService.getWorkoutProgramCategoryById(id));
    }
}
