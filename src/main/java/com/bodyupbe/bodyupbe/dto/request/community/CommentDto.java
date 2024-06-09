package com.bodyupbe.bodyupbe.dto.request.community;

import com.bodyupbe.bodyupbe.dto.request.user.UserDto;
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
    UserDto user;
    PostDto post;
    CommentDto comment;
}
