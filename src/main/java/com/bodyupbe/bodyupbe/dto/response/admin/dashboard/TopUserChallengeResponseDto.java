package com.bodyupbe.bodyupbe.dto.response.admin.dashboard;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class TopUserChallengeResponseDto {
    int id;
    String userName;
    String firstName;
    String lastName;
    String email;
    Long userChallengeCompletedCount;
}
