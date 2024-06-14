package com.bodyupbe.bodyupbe.controller.community;

import com.bodyupbe.bodyupbe.dto.response.community.PostResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.community.BookmarkPostService;
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
@RequestMapping("/api/v1/bookmarkPosts")
@CrossOrigin
public class BookmarkPostController {
    BookmarkPostService bookmarkPostService;
    UserRepository userRepository;

    @PostMapping
    public ResponseEntity<Optional<UserResponseDto>> bookmarkPost(@RequestParam int postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(bookmarkPostService.bookmarkPost(optionalUser.get(), postId));
    }


    @GetMapping
    public ResponseEntity<Set<PostResponseDto>> getBookmarkPost(@RequestParam int userId) {
        return ResponseEntity.ok(bookmarkPostService.getBookmarkPost(userId));
    }
}
