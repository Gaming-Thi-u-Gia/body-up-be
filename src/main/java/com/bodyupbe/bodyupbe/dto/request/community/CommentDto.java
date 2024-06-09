package com.bodyupbe.bodyupbe.dto.request.community;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentDto {
    Integer id;
    String detail;
    int upVote;
    Date createAt;
    CommentDto comment;
}
