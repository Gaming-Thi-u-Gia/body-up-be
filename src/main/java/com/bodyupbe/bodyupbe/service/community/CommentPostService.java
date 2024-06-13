package com.bodyupbe.bodyupbe.service.community;

import com.bodyupbe.bodyupbe.dto.mapper.community.CommentMapper;
import com.bodyupbe.bodyupbe.dto.request.community.CommentRequestDto;
import com.bodyupbe.bodyupbe.dto.response.community.CommentResponseDto;
import com.bodyupbe.bodyupbe.dto.response.community.PostResponseDto;
import com.bodyupbe.bodyupbe.model.community.Comment;
import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.CommentRepository;
import com.bodyupbe.bodyupbe.repository.PostRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentPostService {
    UserRepository userRepository;
    PostRepository postRepository;
    CommentMapper commentMapper;
    CommentRepository commentRepository;
    public CommentResponseDto createComment(CommentRequestDto commentRequestDto, User user, int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));
        Comment comment = Comment.builder()
                .detail(commentRequestDto.getDetail())
                .upVote(commentRequestDto.getUpVote())
                .user(user)
                .post(post)
                .build();
        user.getComments().add(comment);
        post.getComments().add(comment);
        return commentMapper.toCommentResponseDto(commentRepository.save(comment));
    }

    public String deleteComment(int commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
        return "success";
    }


    public List<CommentResponseDto> getAllCommentByPostId(int postId) {
        List<Comment> comment = commentRepository.findCommentByPost_IdOrderByCreatedAtDesc(postId);
        return commentMapper.toListCommentResponseDto(comment);
    }


    public List<CommentResponseDto> getAllCommentByUserId(int userId) {
        List<Comment> comment = commentRepository.findAllByUser_Id(userId);
        return commentMapper.toListCommentResponseDto(comment);
    }


//    public List<CommentResponseDto>



}
