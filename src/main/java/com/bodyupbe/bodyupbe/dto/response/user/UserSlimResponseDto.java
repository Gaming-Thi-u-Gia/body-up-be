package com.bodyupbe.bodyupbe.dto.response.user;

import com.bodyupbe.bodyupbe.model.user.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
public class UserSlimResponseDto {
    Integer id;
    String userName;
    String userName2;
    String firstName;
    String lastName;
    String email;
    String avatar;
    String bio;
    Role role;
    Date createAt;

    public UserSlimResponseDto(Integer id, String userName, String firstName, String lastName, String email, String avatar, String bio, Role role, Date createAt) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.avatar = avatar;
        this.bio = bio;
        this.role = role;
        this.createAt = createAt;
    }
}
