package com.bodyupbe.bodyupbe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDto {
    Integer id;
    String detail;
    int upVote;
    Date createAt;
    UserDto userDto;
    PostDto postDto;
    CommentDto commentDto;
    Set<CommentDto> commentDtos;
}
