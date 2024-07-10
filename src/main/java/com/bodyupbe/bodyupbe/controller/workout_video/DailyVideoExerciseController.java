package com.bodyupbe.bodyupbe.controller.workout_video;

import com.bodyupbe.bodyupbe.dto.response.workout_video.DailyVideoResponseDto;
import com.bodyupbe.bodyupbe.service.daily_video.DailyVideoService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/workout-video")
@CrossOrigin
public class DailyVideoExerciseController {
    DailyVideoService dailyVideoService;
    @GetMapping("/getVideosByDay")
    public ResponseEntity<Set<DailyVideoResponseDto>> getVideosByDay(@RequestParam String day, @RequestParam Integer workoutProgramId) {
        Set<DailyVideoResponseDto> videos = dailyVideoService.getVideosByDay(day, workoutProgramId);
        return ResponseEntity.ok(videos);
    }

}
