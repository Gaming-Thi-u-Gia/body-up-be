package com.bodyupbe.bodyupbe.controller.community;


import com.bodyupbe.bodyupbe.dto.request.community.PostRequestDto;
import com.bodyupbe.bodyupbe.dto.response.community.PostResponseDto;
import com.bodyupbe.bodyupbe.service.community.PostService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/posts")
@CrossOrigin
public class PostController {
    PostService postService;
    @PostMapping("/create")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postDto, @RequestParam int userId, @RequestParam int badgeId, @RequestParam int categoryId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(postService.createPost(postDto, userId, badgeId, categoryId));
    }
    @GetMapping("/getpostByCategory")
    public ResponseEntity <List<PostResponseDto>> getAllPostByCategoryId(@RequestParam int categoryId) {
        return ResponseEntity.ok(postService.getPostAllByCategoryId(categoryId));
    }
    @GetMapping("/getpostByUser")
    public ResponseEntity <List<PostResponseDto>> getAllPostByUserId(@RequestParam int userId) {
        return ResponseEntity.ok(postService.getPostByUserId(userId));
    }
    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestParam int postId) {
        postService.deletePost(postId);
         return ResponseEntity.ok("Post deleted successfully");
    }
    @GetMapping("/getPostById")
    public ResponseEntity <PostResponseDto> getPostById(@RequestParam int postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }



}
