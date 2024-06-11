package com.bodyupbe.bodyupbe.controller.user;

import com.bodyupbe.bodyupbe.dto.request.user.UserProgressPhotoRequestDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserProgressPhotoResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.user.UserService;
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
@RequestMapping("/api/v1/user")
@CrossOrigin
public class UserController {
    UserService userService;
    UserRepository userRepository;

    @PostMapping("/addProgressPhoto")
    public ResponseEntity<Set<UserProgressPhotoResponseDto>> addProgressPhoto(@RequestBody UserProgressPhotoRequestDto userProgressPhotoRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userService.addProgressPhoto(optionalUser.get(), userProgressPhotoRequestDto));
    }
    @GetMapping("/getProgressPhotoById")
    public ResponseEntity<UserProgressPhotoResponseDto> getUserProgressPhotoById(@RequestParam int progressPhotoId) {
        return ResponseEntity.ok(userService.getUserProgressPhotoById(progressPhotoId));
    }
    @GetMapping("/getProgressPhotoByPhotoAngle")
    public ResponseEntity<Set<UserProgressPhotoResponseDto>> getProgressPhotoByPhotoAngle(@RequestParam String photoAngle) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userService.getProgressPhotoByPhotoAngle(optionalUser.get(),photoAngle));
    }

    @GetMapping("/getProgressPhoto")
    public ResponseEntity<Set<UserProgressPhotoResponseDto>> getProgressPhoto() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userService.getProgressPhoto(optionalUser.get()));
    }

    @GetMapping("/getPhotoByUserIdAndPhotoAngle")
    public ResponseEntity<Set<UserProgressPhotoResponseDto>> getUserProgressPhotoById(@RequestParam int userId,@RequestParam String photoAngle) {
        return ResponseEntity.ok(userService.getUserProgressPhotoByUserId(userId,photoAngle));
    }

    @GetMapping("/getPhotoByUserId")
    public ResponseEntity<Set<UserProgressPhotoResponseDto>> getUserProgressPhotoByUserId(@RequestParam int userId) {
        return ResponseEntity.ok(userService.getUserProgressPhotoByUserId(userId));
    }

    @GetMapping("/getUserById")
    public ResponseEntity<UserResponseDto> getUserById(@RequestParam int userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }

    @DeleteMapping("/deleteUserProgressPhotoById")
    public ResponseEntity<User> deleteUserProgressPhotoById(@RequestParam int progressPhotoId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        userService.deleteUserProgressPhotoById(optionalUser.get(), progressPhotoId);
        return ResponseEntity.ok(optionalUser.get());
    }

    @PutMapping("/updateUserProgressPhoto")
    public ResponseEntity<UserProgressPhotoResponseDto> updateUserProgressPhoto(@RequestParam int progressPhotoId, @RequestBody UserProgressPhotoRequestDto userProgressPhotoRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(userService.updateUserProgressPhoto(optionalUser.get(), progressPhotoId, userProgressPhotoRequestDto));
    }
    @GetMapping("/getUserByUsername2")
    public ResponseEntity<UserResponseDto> getUserByUsername2(@RequestParam String username2) {
        return ResponseEntity.ok(userService.getUserByUsername2(username2));
    }
}
