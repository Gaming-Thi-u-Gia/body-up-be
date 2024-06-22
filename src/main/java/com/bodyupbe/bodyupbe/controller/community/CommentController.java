package com.bodyupbe.bodyupbe.controller.community;

import com.bodyupbe.bodyupbe.dto.request.community.CommentRequestDto;
import com.bodyupbe.bodyupbe.dto.response.community.CommentResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.community.CommentPostService;
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
@RequestMapping("/api/v1/comments")
@CrossOrigin
public class CommentController {
    UserRepository userRepository;
    CommentPostService commentPostService;


    @PostMapping("/create")
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto request,  @RequestParam int postId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipal = authentication.getName();
        Optional<User> optionalUser = userRepository.findByEmail(currentPrincipal);
        if(optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(commentPostService.createComment(request, optionalUser.get(),postId ));
    }

    @DeleteMapping("deleteComment")
    public ResponseEntity<String> deleteComment(@RequestParam int commentId) {
        commentPostService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted successfully");
    }

    @GetMapping("/getChildComments")
    public ResponseEntity <List<CommentResponseDto>> getChildComments(@RequestParam int parentId) {
        return ResponseEntity.ok(commentPostService.getChildComments(parentId));
    }

    @GetMapping("/getAllComments")
    public ResponseEntity <List<CommentResponseDto>> getAllComments(@RequestParam int postId) {
        return ResponseEntity.ok(commentPostService.getAllCommentByPostId(postId));
    }
    // Fix get
    @GetMapping("/getAllByUserId")
    public ResponseEntity <List<CommentResponseDto>> getAllCommentsByUserId(@RequestParam int userId) {
        return ResponseEntity.ok(commentPostService.getAllCommentByUserId(userId));
    }

    @PutMapping("/upvoteComment")
    public ResponseEntity<CommentResponseDto> upvoteComment(@RequestParam int commentId, @RequestParam int upVote) {
        return ResponseEntity.ok(commentPostService.upvoteComment(commentId,upVote));
    }

    @GetMapping("/getCommentById")
    public ResponseEntity <CommentResponseDto> getCommentById(@RequestParam int commentId) {
        return ResponseEntity.ok(commentPostService.getCommentById(commentId));
    }


    @GetMapping("/getRootById")
    public ResponseEntity<Optional<CommentResponseDto>> getRootById(@RequestParam int commentId) {
        return ResponseEntity.ok(commentPostService.getRootComment(commentId));
    }

}
