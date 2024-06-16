package com.bodyupbe.bodyupbe.dto.mapper.user;
import com.bodyupbe.bodyupbe.dto.request.user.UserProgressPhotoRequestDto;
import com.bodyupbe.bodyupbe.dto.request.user.UserRequestDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserChallengeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserDailyChallengeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserProgressPhotoResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.user.UserChallenge;
import com.bodyupbe.bodyupbe.model.user.UserDailyChallenge;
import com.bodyupbe.bodyupbe.model.user.UserProgressPhoto;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserRequestDto userRequestDto);

    UserResponseDto toUserResponseDto(User user);

    UserProgressPhoto toUserProgressPhoto(UserProgressPhotoRequestDto userProgressPhotoRequestDto);

    UserProgressPhotoResponseDto toUserProgressPhotoResponseDto(UserProgressPhoto userProgressPhoto);

    Set<UserProgressPhotoResponseDto> toListUserProgressPhotoResponseDto(Set<UserProgressPhoto> userProgressPhotos);

    UserChallengeResponseDto toUserChallengeResponseDto(UserChallenge userChallenge);

    Set<UserChallengeResponseDto> toListUserChallengeResponseDto(Set<UserChallenge> userChallenges);

    Set<UserDailyChallengeResponseDto> toListUserDailyChallengeResponseDto(Set<UserDailyChallenge> userDailyChallenges);
}
