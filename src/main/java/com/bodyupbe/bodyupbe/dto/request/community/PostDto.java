package com.bodyupbe.bodyupbe.dto.request.community;

import com.bodyupbe.bodyupbe.dto.request.user.UserChallengeDto;
import com.bodyupbe.bodyupbe.dto.request.user.UserDto;
import com.bodyupbe.bodyupbe.model.user.UserChallenge;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDto {
    Integer id;
    String title;
    String description;
    String imgBefore;
    String imgAfter;
    Date dayBefore;
    Date dayAfter;
    Date createdAt;
    UserDto user;
    CategoryCommunityDto categoryCommunity;
    BadgeDto badge;
    Set<CommentDto> comments;
    Set<UserDto> bookmarkUsers;
    Set<OtherImagePostDto> otherImagePosts;
    Set<UserChallengeDto> userChallenges;
}
