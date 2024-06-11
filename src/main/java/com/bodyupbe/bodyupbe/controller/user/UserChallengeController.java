package com.bodyupbe.bodyupbe.controller.user;

import com.bodyupbe.bodyupbe.dto.request.user.UserChallengeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserChallengeResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.user.UserChallengeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

    // Add a new challenge to the user
    @PostMapping("/addChallenge")
    public ResponseEntity<UserChallengeResponseDto> addChallenge(@RequestBody UserChallengeRequestDto userChallengeRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userChallengeService.addUserChallenge(optionalUser.get(), userChallengeRequestDto.getWorkoutProgram().getId()));
    }

    // Get all challenges of the user
    @GetMapping("/getAllChallenges")
    public ResponseEntity<Set<UserChallengeResponseDto>> getAllChallenges() {
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
}
