package com.bodyupbe.bodyupbe.controller.workout_video;

import com.bodyupbe.bodyupbe.dto.response.workout_video.ObjectVideo.ObjectVideoSetResponse;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoCategoryResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoCategoryTableResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoSlimResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.video.VideoCategoryService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@Slf4j
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/workout-video")
@CrossOrigin
public class VideoCategoryController {
    VideoCategoryService videoCategoryService;
    UserRepository userRepository;
    @GetMapping("/getCategoryByVideo")
    public ResponseEntity<Set<VideoCategoryResponseDto>> getCategoryByVideo(@RequestParam int videoId) {
        log.info("getVideoByCategory: " + videoId);
        return ResponseEntity.ok(videoCategoryService.getCategoryByVideo(videoId));
    }

    @GetMapping("/videoTable")
    public ResponseEntity<Set<VideoCategoryTableResponseDto>> getAllVideoCategoriesWithEachType() {
        return ResponseEntity.ok(videoCategoryService.getAllVideoCategoriesWithEachType());
    }

    @GetMapping("/category")
    public ResponseEntity<ObjectVideoSetResponse<VideoSlimResponseDto>> getVideoByCategory(@RequestParam Set<Integer> categoryIds, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "4") int pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.ok(videoCategoryService.getVideoByCategory(categoryIds, user.isPresent() ? Optional.of(user.get().getId()) : Optional.empty(), pageNo, pageSize));
    }
}
