package com.bodyupbe.bodyupbe.dto.response.community;
import com.bodyupbe.bodyupbe.dto.request.community.BadgeDto;
import com.bodyupbe.bodyupbe.dto.request.community.CategoryCommunityDto;
import com.bodyupbe.bodyupbe.dto.request.community.OtherImagePostDto;
import com.bodyupbe.bodyupbe.dto.request.user.UserChallengeDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostResponseDto {
    Integer id;
    String title;
    String description;
    String imgBefore;
    String imgAfter;
    Date dayBefore;
    Date dayAfter;
    Date createdAt;
    UserResponseDto user;
    CategoryCommunityDto categoryCommunity;
    BadgeDto badge;
    Set<CommentResponseDto> comments;
    Set<OtherImagePostDto> otherImagePosts;
    Set<UserChallengeDto> userChallenges;
}
