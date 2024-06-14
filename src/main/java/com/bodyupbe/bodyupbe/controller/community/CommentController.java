package com.bodyupbe.bodyupbe.controller.community;

import com.bodyupbe.bodyupbe.dto.request.community.CommentRequestDto;
import com.bodyupbe.bodyupbe.dto.response.community.CommentResponseDto;
import com.bodyupbe.bodyupbe.service.community.CommentPostService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/comments")
@CrossOrigin
public class CommentController {

    CommentPostService commentPostService;

    @PostMapping
    public ResponseEntity<CommentResponseDto> createComment(@RequestBody CommentRequestDto request, @RequestParam int userId, @RequestParam int postId) {
        return ResponseEntity.ok(commentPostService.createComment(request, userId, postId ));
    }
}
