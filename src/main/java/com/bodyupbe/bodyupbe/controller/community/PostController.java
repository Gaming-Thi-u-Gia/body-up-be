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

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        return ResponseEntity.ok(postService.createPost(postDto, userId, badgeId, categoryId));
    }
    @GetMapping("/getpostByCategory")
    public ResponseEntity <List<PostDto>> getAllPostByCategoryId(@RequestParam int categoryId) {
        return ResponseEntity.ok(postService.getPostAllByCategoryId(categoryId));
    }
    @GetMapping("/getpostByUser")
    public ResponseEntity <List<PostDto>> getAllPostByUserId(@RequestParam int userId) {
        return ResponseEntity.ok(postService.getPostByUserId(userId));
    }
    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestParam int postId) {
        postService.deletePost(postId);
         return ResponseEntity.ok("Post deleted successfully");
    }
    @GetMapping("/getPostById")
    public ResponseEntity <PostDto> getPostById(@RequestParam int postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }



}
