package com.bodyupbe.bodyupbe.dto.request.user;

import com.bodyupbe.bodyupbe.dto.request.workout_video.BookmarkVideoDto;
import com.bodyupbe.bodyupbe.dto.request.community.BookmarkPostDto;
import com.bodyupbe.bodyupbe.dto.request.community.CommentDto;
import com.bodyupbe.bodyupbe.dto.request.community.PostDto;
import com.bodyupbe.bodyupbe.dto.request.recipe.BookmarkRecipeDto;
import com.bodyupbe.bodyupbe.dto.request.recipe.RatingRecipeDto;
import com.bodyupbe.bodyupbe.model.user.Role;
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
