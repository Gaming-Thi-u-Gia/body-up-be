package com.bodyupbe.bodyupbe.controller;

import com.bodyupbe.bodyupbe.dto.response.NotificationResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectSetResponse;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.NotificationService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/notification")
@CrossOrigin
public class NotificationController {
    NotificationService notificationService;
    UserRepository userRepository;
    @GetMapping
    public ResponseEntity<ObjectSetResponse<NotificationResponseDto>> getNotifications(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "5 ") int pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(notificationService.getNotifications(user.get().getId(), pageNo, pageSize));
    }
}
