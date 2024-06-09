package com.bodyupbe.bodyupbe.dto.request.user;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChallengeDto {
    int id;
    String status;
}
