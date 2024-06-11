package com.bodyupbe.bodyupbe.dto.mapper.community;

import com.bodyupbe.bodyupbe.dto.response.community.CommentResponseDto;
import com.bodyupbe.bodyupbe.model.community.Comment;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentResponseDto toCommentResponseDto(Comment comment);

    List<CommentResponseDto> toListCommentResponseDto(List<Comment> comments);


    Set<CommentResponseDto> toSetCommentResponseDto(Set<Comment> comments);

}
