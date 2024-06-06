package com.bodyupbe.bodyupbe.controller.community;


import com.bodyupbe.bodyupbe.dto.request.community.PostDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.service.AuthenticationResponse;
import com.bodyupbe.bodyupbe.service.community.PostService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/posts")
@CrossOrigin
public class PostController {
    PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @RequestParam int userId, @RequestParam int badgeId, @RequestParam int categoryId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return ResponseEntity.ok(postService.createPost(postDto, userId, badgeId, categoryId));
    }


//    public ResponseEntity<String> updateAvatar(@RequestBody User request) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipalName);
//        if (optionalUser.isPresent()) {
//            User user = optionalUser.get();
//            user.setAvatar(request.getAvatar());
//            userRepository.save(user);
//            return ResponseEntity.ok("Avatar updated successfully");
//        } else {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//    }
}
