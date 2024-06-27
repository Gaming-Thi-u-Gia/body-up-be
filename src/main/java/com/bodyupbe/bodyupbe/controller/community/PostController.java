package com.bodyupbe.bodyupbe.controller.community;


import com.bodyupbe.bodyupbe.dto.request.community.PostRequestDto;
import com.bodyupbe.bodyupbe.dto.response.community.PostCommentSlimDto;
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
    public ResponseEntity <List<PostResponseDto>> getAllPostByCategoryId(@RequestParam int categoryId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        if(optionalUser.isPresent()){
            return ResponseEntity.ok(postService.getPostAllByCategoryId(Optional.of(optionalUser.get().getId()),categoryId,page,size));
        }
        else {
            return ResponseEntity.ok(postService.getPostAllByCategoryId(Optional.empty(),categoryId,page,size));
        }
    }
    @GetMapping("/getAllPostByUser")
    public ResponseEntity <List<PostResponseDto>> getAllPostByUserId(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(postService.getPostByUserId(Optional.of(optionalUser.get().getId()),page,size));
    }

    @DeleteMapping("/deletePost")
    public ResponseEntity<String> deletePost(@RequestParam int postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        postService.deletePost(postId, optionalUser.get());
        return ResponseEntity.ok("Post deleted successfully");
    }
    @GetMapping("/getPostById")
    public ResponseEntity <PostResponseDto> getPostById(@RequestParam int postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        if(optionalUser.isPresent()){
            return ResponseEntity.ok(postService.getPostById(Optional.of(optionalUser.get().getId()), postId));
        }
        else {
            return ResponseEntity.ok(postService.getPostById(Optional.empty(), postId));
        }
    }

    @GetMapping("/getAllPostBookmark")
    public ResponseEntity <List<PostResponseDto>> getAllPostBookmark(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        return ResponseEntity.ok(postService.getAllPostBookmarkByUserId(Optional.of(optionalUser.get().getId()), page, size));

    }
    @GetMapping("/getAllPostByBadgeId")
    public ResponseEntity <List<PostResponseDto>> getAllPostByBadgeId(@RequestParam int badgeId) {
        return ResponseEntity.ok(postService.getAllPostByBadgeId(badgeId));
    }

    @GetMapping("/getPostsCommented")
    public ResponseEntity <List<PostCommentSlimDto>> getPostsCommented() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(postService.getPostsCommentedAndCommentByUser(optionalUser.get()));
    }

    @PutMapping("/editPost")
    public ResponseEntity<PostResponseDto> editPost(@RequestBody PostRequestDto request, @RequestParam int postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(postService.editPost(request, optionalUser.get(), postId));
    }

    @GetMapping("/filterPost")
    public ResponseEntity <List<PostResponseDto>> filterPostByBadgeNameAndCategoryId(@RequestParam String badgeName, @RequestParam int categoryId, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        if(optionalUser.isPresent()){
            return ResponseEntity.ok(postService.getPostByBadgeNameAndCategoryId(badgeName, categoryId, Optional.of(optionalUser.get().getId()),page,size));
        }
        else {
            return ResponseEntity.ok(postService.getPostByBadgeNameAndCategoryId(badgeName, categoryId, Optional.empty(),page,size));
        }
    }



}
