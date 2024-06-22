package com.bodyupbe.bodyupbe.controller;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.response.TopicResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.TopicWorkoutProgramResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.TopicVideoResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.AuthenticationResponse;
import com.bodyupbe.bodyupbe.service.TopicService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
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
public class TopicController {
    TopicService topicService;
    UserRepository userRepository;

    @GetMapping("/getTopicAll")
    public ResponseEntity<List<TopicResponseDto>> getTopicAll() {
        return ResponseEntity.ok(topicService.getAllTopic());
    }

    @GetMapping("/getTopicById")
    public ResponseEntity<TopicResponseDto> getTopicById(@RequestParam int id) {
        return ResponseEntity.ok(topicService.getTopicById(id));
    }

    @GetMapping("/deleteTopic")
    public ResponseEntity<String> deleteTopic(@RequestParam int id) {
        topicService.deleteTopic(id);
        return ResponseEntity.ok("Topic deleted successfully");
    }

    @GetMapping("/updateTopic")
    public ResponseEntity<TopicResponseDto> updateTopic(@RequestBody TopicDto topicDto) {
        return ResponseEntity.ok(topicService.updateTopic(topicDto));
    }

    @PostMapping("/createTopic")
    public ResponseEntity<TopicResponseDto> createTopic(@RequestBody TopicDto topicDto) {
        return ResponseEntity.ok(topicService.createTopic(topicDto));
    }

    @GetMapping("/getTopicForVideo")
    public ResponseEntity<Set<TopicResponseDto>> getTopicForVideo() {
        return ResponseEntity.ok(topicService.getTopicForVideo());
    }

    @GetMapping("/getTopicByVideoId")
    public ResponseEntity<Set<TopicResponseDto>> getTopicByVideoId(@RequestParam int videoId) {
        return ResponseEntity.ok(topicService.getTopicByVideoId(videoId));
    }

    @GetMapping("/getTopicForWorkout")
    public ResponseEntity<Set<TopicResponseDto>> getTopicForWorkout() {
        return ResponseEntity.ok(topicService.getTopicForWourkoutProgram());
    }

    @GetMapping("/getTopicWithWorkoutProgram")
    public ResponseEntity<Set<TopicWorkoutProgramResponseDto>> getTopicWithWorkoutProgram() {
        return ResponseEntity.ok(topicService.getTopicWithWorkoutProgram());
    }

    @GetMapping("/getTopicWithWorkoutVideo")
    public ResponseEntity<Set<TopicVideoResponseDto>> getTopicWithWorkoutVideo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        if(optionalUser.isPresent()) {
            return ResponseEntity.ok(topicService.getTopicWithWorkoutVideo(optionalUser));
        }
        return ResponseEntity.ok(topicService.getTopicWithWorkoutVideo(Optional.empty()));
    }
    @GetMapping("/getTopicWithWorkoutProgramById")
    public ResponseEntity<Set<TopicWorkoutProgramResponseDto>> getTopicWithWorkoutProgramById(@RequestParam int topicId) {
        return ResponseEntity.ok(topicService.getTopicWithWorkoutProgramById(topicId));
    }
}
