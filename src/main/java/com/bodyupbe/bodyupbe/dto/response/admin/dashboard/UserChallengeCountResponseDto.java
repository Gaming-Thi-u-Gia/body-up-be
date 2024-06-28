package com.bodyupbe.bodyupbe.dto.response.admin.dashboard;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserChallengeCountResponseDto {
    Set<UserChallengeStatusCountResponseDto> userChallengeStatusCountCompleted;
    Set<UserChallengeStatusCountResponseDto> userChallengeStatusCountUncompleted;
}
