package com.bodyupbe.bodyupbe.dto.request.community;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentRequestDto {
    Integer id;
    String detail;
    int upVote;
    Date createAt;
    Integer parentId;
//    User user
}
