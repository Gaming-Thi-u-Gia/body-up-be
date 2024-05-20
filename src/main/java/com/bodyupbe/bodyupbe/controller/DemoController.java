package com.bodyupbe.bodyupbe.controller;

import com.bodyupbe.bodyupbe.model.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class DemoController {
    private final UserRepository userRepository;
    @GetMapping("/api/v1/demo")
    public ResponseEntity<Optional<User>> demo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return ResponseEntity.ok(userRepository.findByEmail(currentPrincipalName));
    }
    @PutMapping("/api/v1/avatar")
    public ResponseEntity<String> updateAvatar(@RequestBody User request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setAvatar(request.getAvatar());
            userRepository.save(user);
            return ResponseEntity.ok("Avatar updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}