package com.bodyupbe.bodyupbe.controller.community;


import com.bodyupbe.bodyupbe.dto.request.community.PostRequestDto;
import com.bodyupbe.bodyupbe.dto.response.community.PostResponseDto;
import com.bodyupbe.bodyupbe.dto.response.community.PostSlimResponse;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.community.PostService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/posts")
@CrossOrigin
public class PostController {
    PostService postService;
    UserRepository userRepository;
    @PostMapping("/create")
    public ResponseEntity<PostResponseDto> createPost(@RequestBody PostRequestDto postDto, @RequestParam int badgeId, @RequestParam int categoryId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        if(optionalUser.isEmpty()) {
           throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(postService.createPost(postDto,optionalUser.get() ,badgeId, categoryId));
    }

    //
    @GetMapping("/getAllPostByCategory")
    public ResponseEntity <List<PostResponseDto>> getAllPostByCategoryId(@RequestParam int categoryId) {
        return ResponseEntity.ok(postService.getPostAllByCategoryId(categoryId));
    }
    @GetMapping("/getAllPostByUser")
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

    @GetMapping("/getAllPostByBadgeId")
    public ResponseEntity <List<PostResponseDto>> getAllPostByBadgeId(@RequestParam int badgeId) {
        return ResponseEntity.ok(postService.getAllPostByBadgeId(badgeId));
    }



}
