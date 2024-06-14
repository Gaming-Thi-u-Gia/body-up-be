package com.bodyupbe.bodyupbe.controller.workout_video;

import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoCategoryResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoResponseDto;
import com.bodyupbe.bodyupbe.service.video.VideoCategoryService;
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
public class VideoCategoryController {
    VideoCategoryService videoCategoryService;
    @GetMapping("/getCategoryByVideo")
    public ResponseEntity<Set<VideoCategoryResponseDto>> getCategoryByVideo(@RequestParam int videoId) {
        log.info("getVideoByCategory: " + videoId);
        return ResponseEntity.ok(videoCategoryService.getCategoryByVideo(videoId));
    }
}
