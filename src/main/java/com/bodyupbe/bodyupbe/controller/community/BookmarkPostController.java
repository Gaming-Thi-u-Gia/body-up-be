package com.bodyupbe.bodyupbe.controller.community;

import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.service.community.BookmarkPostService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/bookmarkPosts")
@CrossOrigin
public class BookmarkPostController {
    BookmarkPostService bookmarkPostService;


    @PostMapping
    public ResponseEntity<Optional<User>> bookmarkPost(int userId, int postId) {
        return ResponseEntity.ok(bookmarkPostService.bookmarkPost(userId, postId));
    }
}
