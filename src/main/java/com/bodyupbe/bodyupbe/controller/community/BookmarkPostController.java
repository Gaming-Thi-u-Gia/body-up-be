package com.bodyupbe.bodyupbe.controller.community;

import com.bodyupbe.bodyupbe.dto.response.community.PostResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserResponseAndSetPostSlimResponseDto;
import com.bodyupbe.bodyupbe.service.community.BookmarkPostService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/bookmarkPosts")
@CrossOrigin
public class BookmarkPostController {
    BookmarkPostService bookmarkPostService;


    @PostMapping
    public ResponseEntity<Optional<UserResponseAndSetPostSlimResponseDto>> bookmarkPost(@RequestParam int userId, @RequestParam int postId) {
        return ResponseEntity.ok(bookmarkPostService.bookmarkPost(userId, postId));
    }


    @GetMapping
    public ResponseEntity<Set<PostResponseDto>> getBookmarkPost(@RequestParam int userId) {
        return ResponseEntity.ok(bookmarkPostService.getBookmarkPost(userId));
    }
}
