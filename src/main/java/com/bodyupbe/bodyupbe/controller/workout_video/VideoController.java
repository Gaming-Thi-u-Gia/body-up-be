package com.bodyupbe.bodyupbe.controller.workout_video;

import com.bodyupbe.bodyupbe.dto.request.workout_video.VideoRequestDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.ObjectVideo.ObjectVideoResponse;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoBookmarkResponseSlim;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoSlimResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.video.BookmarkService;
import com.bodyupbe.bodyupbe.service.video.VideoService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Slf4j
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/workout-video")
@CrossOrigin
public class VideoController {
    VideoService videoService;
    BookmarkService bookmarkService;
    UserRepository userRepository;

    @GetMapping("/getVideoAll")
    public ResponseEntity<List<VideoResponseDto>> getVideoAll() {
        return ResponseEntity.ok(videoService.getVideoAll());
    }
    @GetMapping("/getVideoById")
    public ResponseEntity<VideoResponseDto> getVideoById(@RequestParam int id) {
        return ResponseEntity.ok(videoService.getVideoById(id));
    }
    @GetMapping("/deleteVideo")
    public ResponseEntity<String> deleteVideo(@RequestParam int id) {
        videoService.deleteVideo(id);
        return ResponseEntity.ok("Video deleted successfully");
    }
    @PutMapping("/updateVideo")
    public ResponseEntity<VideoResponseDto> updateVideo(@RequestBody VideoRequestDto videoRequestDto) {
        return ResponseEntity.ok(videoService.updateVideo(videoRequestDto));
    }


    @GetMapping("/getVideoByCategory")
    public ResponseEntity<Set<VideoResponseDto>> getVideoByCategory(@RequestParam int categoryId) {
        log.info("getVideoByCategory: " + categoryId);
        return ResponseEntity.ok(videoService.getVideoByCategory(categoryId));
    }

    @GetMapping("/getVideoByTopic")
    public ResponseEntity<Set<VideoResponseDto>> getVideoByTopic(@RequestParam int topicId) {
        log.info("getVideoByTopic: " + topicId);
        return ResponseEntity.ok(videoService.getVideoByTopic(topicId));
    }

    @GetMapping("/searchVideo")
    public ResponseEntity<List<VideoResponseDto>> searchVideo(@RequestParam String name) {
        return ResponseEntity.ok(videoService.searchVideo(name));
    }

    @PostMapping("/getBookmark")
    public ResponseEntity<VideoBookmarkResponseSlim> getBookmark(@RequestParam int userId, @RequestParam String url) {
        return ResponseEntity.ok(bookmarkService.getBookmarkVideo(userId, url));
    }

    @GetMapping("/getWorkoutVideoWithBookmark")
    public ResponseEntity<Set<VideoResponseDto>> getWorkoutVideoWithBookmark() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();

        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        return optionalUser.map(user ->
                        ResponseEntity.ok(videoService.getAllBookmarkedVideo(user.getId())))
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null));
    }

}
