package com.bodyupbe.bodyupbe.dto.request.user;

import com.bodyupbe.bodyupbe.model.user.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserRequestDto {
    String userName;
    String firstName;
    String lastName;
    String email;
    String password;
    String avatar;
    String bio;
    Role role;
    Date createAt;
}
