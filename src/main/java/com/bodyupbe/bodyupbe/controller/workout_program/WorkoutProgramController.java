package com.bodyupbe.bodyupbe.controller.workout_program;

import com.bodyupbe.bodyupbe.dto.request.workout_program.WorkoutProgramRequestDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.ObjectWorkoutProgram.ObjectWorkoutProgramSetResponse;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramCategoryResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramSlimResponse;
import com.bodyupbe.bodyupbe.service.workout_program.WorkoutProgramService;
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
public class WorkoutProgramController {
    WorkoutProgramService workoutProgramService;
    @GetMapping("/getAllWorkoutProgram")
    public ResponseEntity<List<WorkoutProgramResponseDto>> getAllWorkoutProgram() {
        return ResponseEntity.ok(workoutProgramService.getAllWorkoutProgram());
    }

    @GetMapping("/getWorkoutProgramById")
    public ResponseEntity<WorkoutProgramResponseDto> getWorkoutProgramById(@RequestParam int id) {
        return ResponseEntity.ok(workoutProgramService.getWorkoutProgramById(id));
    }

    @DeleteMapping("/deleteWorkoutProgram")
    public ResponseEntity<String> deleteWorkoutProgram(@RequestParam int id) {
        workoutProgramService.deleteWorkoutProgram(id);
        return ResponseEntity.ok("Workout program deleted successfully");
    }

    @PutMapping("/updateWorkoutProgram")
    public ResponseEntity<WorkoutProgramResponseDto> updateWorkoutProgram(@RequestBody WorkoutProgramRequestDto workoutProgramRequestDto) {
        return ResponseEntity.ok(workoutProgramService.updateWorkoutProgram(workoutProgramRequestDto));
    }

    @GetMapping("/getWorkoutProgramByWorkoutProgramCategory")
    public ResponseEntity<WorkoutProgramCategoryResponseDto> getWorkoutProgramByWorkoutProgramCategory(@RequestParam int workoutProgramCategoryId) {
        return ResponseEntity.ok(workoutProgramService.getWorkoutProgramByWorkoutProgramCategory(workoutProgramCategoryId));
    }

    @GetMapping("/getWorkoutProgramByTopic")
    public ResponseEntity<Set<WorkoutProgramResponseDto>> getWorkoutProgramByTopic(@RequestParam int topicId) {
        return ResponseEntity.ok(workoutProgramService.getWorkoutProgramByTopic(topicId));
    }

    @GetMapping("/searchWorkoutProgram")
    public ResponseEntity<List<WorkoutProgramResponseDto>> searchWorkoutProgram(@RequestParam String name) {
        return ResponseEntity.ok(workoutProgramService.searchWorkoutProgram(name));
    }

    @GetMapping("/category-workout-program")
    public ResponseEntity<ObjectWorkoutProgramSetResponse<WorkoutProgramSlimResponse>> getWorkoutProgramCategory(@RequestParam Set<Integer> categoryIds, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "4") int pageSize) {
        return ResponseEntity.ok(workoutProgramService.getWorkoutProgramByCategory(categoryIds, pageNo, pageSize));
    }
}
