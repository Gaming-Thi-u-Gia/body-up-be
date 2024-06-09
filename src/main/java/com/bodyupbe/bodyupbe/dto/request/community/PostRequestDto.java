package com.bodyupbe.bodyupbe.dto.request.community;

import com.bodyupbe.bodyupbe.dto.request.user.UserChallengeDto;
import com.bodyupbe.bodyupbe.dto.request.user.UserRequestDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostRequestDto {
    Integer id;
    String title;
    String description;
    String imgBefore;
    String imgAfter;
    Date dayBefore;
    Date dayAfter;
    Date createdAt;
    UserRequestDto user;
    CategoryCommunityDto categoryCommunity;
    BadgeDto badge;
    Set<CommentDto> comments;
    Set<OtherImagePostDto> otherImagePosts;
    Set<UserChallengeDto> userChallenges;
}
