package com.bodyupbe.bodyupbe.dto.request;

import com.bodyupbe.bodyupbe.model.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDto  {
    Integer id;
    String userName;
    String firstName;
    String lastName;
    String email;
    String password;
    String avatar;
    String bio;
    Role role;
    Date createAt;
    Set<UserChallengeDto> userChallengeDtos;
    Set<UserProgressPhotoDto> userProgressPhotoDtos;
    Set<BookmarkPostDto> bookmarkPostDtos;
    Set<PostDto> postDtos;
    Set<UserDailyChallengeDto> userDailyChallengeDtos;
    Set<BookmarkRecipeDto> bookmarkRecipeDto;
    Set<RatingRecipeDto> ratingRecipeDtos;
    Set<BookmarkVideoDto> bookmarkVideoDtos;
    Set<CommentDto> commentDtos;
}
