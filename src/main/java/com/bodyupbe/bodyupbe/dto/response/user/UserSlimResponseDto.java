package com.bodyupbe.bodyupbe.dto.response.user;

import com.bodyupbe.bodyupbe.model.user.Role;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserSlimResponseDto {
    Integer id;
    String userName;
    String firstName;
    String lastName;
    String email;
    String avatar;
    String bio;
    Role role;
    Date createAt;
}
