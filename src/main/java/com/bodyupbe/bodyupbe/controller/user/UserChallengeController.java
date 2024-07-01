package com.bodyupbe.bodyupbe.controller.user;

import com.bodyupbe.bodyupbe.dto.request.user.UserChallengeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserChallengeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserChallengeSlimResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserDailyChallengeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramSlimResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.DailyVideoResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.daily_video.DailyVideoService;
import com.bodyupbe.bodyupbe.service.user.UserChallengeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/userChallenge")
@CrossOrigin

public class UserChallengeController {
    UserChallengeService userChallengeService;
    UserRepository userRepository;
    private final DailyVideoService dailyVideoService;

    // Add a new challenge to the user
    @PostMapping("/addChallenge")
    public ResponseEntity<UserChallengeSlimResponseDto> addChallenge(@RequestParam int workoutProgramId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userChallengeService.addUserChallenge(optionalUser.get(), workoutProgramId));
    }

    // Get all challenges of the user
    @GetMapping("/getAllChallenges")
    public ResponseEntity<Set<UserChallengeSlimResponseDto>> getAllChallenges() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userChallengeService.getAllUserChallenges(optionalUser.get()));
    }

    // Delete a challenge of the user
    @DeleteMapping("/deleteChallenge")
    public ResponseEntity<String> deleteChallenge(@RequestParam int challengeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        userChallengeService.deleteUserChallenge(optionalUser.get(), challengeId);
        return ResponseEntity.ok("Challenge deleted successfully");
    }

    // Update a challenge of the user
    @PutMapping("/updateChallenge")
    public ResponseEntity<UserChallengeResponseDto> updateChallenge(@RequestBody UserChallengeRequestDto userChallengeRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userChallengeService.updateUserChallenge(optionalUser.get(), userChallengeRequestDto));
    }

    //Get uncompleted challenge of the user
    @GetMapping("/getUncompletedChallenge")
    public ResponseEntity<UserChallengeSlimResponseDto> getUncompletedChallenge() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userChallengeService.getUncompletedChallenge(optionalUser.get()));
    }
    //Get completed challenge of the user
    @GetMapping("/getCompletedChallenge")
    public ResponseEntity<Set<UserChallengeSlimResponseDto>> getCompletedChallenge() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userChallengeService.getCompletedChallenge(optionalUser.get()));
    }

    //getDailyVideoByDay
    @GetMapping("/getDailyVideoByDay")
    public ResponseEntity<Set<DailyVideoResponseDto>> getDailyVideoByDay(@RequestParam String day,@RequestParam Integer workoutProgramId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(dailyVideoService.getDailyVideoByDay(optionalUser.get(), day,workoutProgramId));
    }
    //findByUserAndWorkoutProgram
    @GetMapping("/findByUserAndWorkoutProgram")
    public ResponseEntity<Set<UserDailyChallengeResponseDto>> findByUserAndWorkoutProgram(@RequestParam Integer workoutProgramId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userChallengeService.findByUserAndWorkoutProgram(optionalUser.get().getId(), workoutProgramId));
    }
    //get all workout programs
    @GetMapping("/getAllWorkoutPrograms")
    public ResponseEntity<List<WorkoutProgramSlimResponseDto>> getAllWorkoutPrograms() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userChallengeService.getAllWorkoutPrograms(optionalUser.get()));
    }
    //getAllDay
    @GetMapping("/getAllDay")
    public ResponseEntity<Set<UserDailyChallengeResponseDto>> getAllDay(@RequestParam Integer workoutProgramId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userChallengeService.getAllDay(optionalUser.get(),workoutProgramId));
    }

    //get first uncomplete challenge
    @GetMapping("/getFirstUncompletedChallenge")
    public ResponseEntity<UserDailyChallengeResponseDto> getFirstUncompletedChallenge() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userChallengeService.getFirstUncompletedChallenge(optionalUser.get()));
    }
    //mark challenge as completed
    @PutMapping("/markChallengeAsCompleted")
    public void markChallengeAsCompleted(@RequestParam int challengeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        userChallengeService.markChallengeAsCompleted(optionalUser.get(), challengeId);
    }

    //updateDailyVideoStatus
    @PutMapping("/updateDailyVideoStatus")
    public void updateDailyVideoStatus(@RequestParam int dailyVideoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        dailyVideoService.updateDailyVideoStatus(dailyVideoId, optionalUser.get().getId());
    }
}
