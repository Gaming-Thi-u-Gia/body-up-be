package com.bodyupbe.bodyupbe.dto.request.user;

import com.bodyupbe.bodyupbe.dto.request.community.CommentDto;
import com.bodyupbe.bodyupbe.dto.request.community.PostDto;
import com.bodyupbe.bodyupbe.dto.request.recipe.RatingRecipeDto;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.VideoDto;
import com.bodyupbe.bodyupbe.model.user.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
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

    Set<UserChallengeDto> userChallenges;
    Set<UserProgressPhotoDto> userProgressPhotos;
    Set<PostDto> posts;
    Set<UserDailyChallengeDto> userDailyChallenges;
    Set<RatingRecipeDto> ratingRecipes;
    Set<CommentDto> comments;
    Set<PostDto> bookmarkPosts;
    Set<VideoDto> bookmarkVideos;
    Set<RecipeDto> bookmarkRecipes;
}
