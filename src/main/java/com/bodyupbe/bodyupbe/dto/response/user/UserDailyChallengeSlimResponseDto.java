package com.bodyupbe.bodyupbe.dto.response.user;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDailyChallengeSlimResponseDto {
    int id;
    String status;
}
