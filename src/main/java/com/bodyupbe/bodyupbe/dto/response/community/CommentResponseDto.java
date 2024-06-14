package com.bodyupbe.bodyupbe.dto.response.community;

import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponseDto {
    Integer id;
    String detail;
    int upVote;
    Date createAt;
    UserSlimResponseDto user;
}
