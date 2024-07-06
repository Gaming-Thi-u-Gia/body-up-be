package com.bodyupbe.bodyupbe.dto.response.community;

import com.bodyupbe.bodyupbe.dto.request.community.BadgeDto;
import com.bodyupbe.bodyupbe.dto.request.community.CategoryCommunityDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostCommentSlimDto {
    Integer id;
    String title;
    String description;
    Date createdAt;
    BadgeDto badge;
    CategoryCommunityDto categoryCommunity;
    Set<CommentResponseDto> comments;
}
