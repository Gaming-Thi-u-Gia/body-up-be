package com.bodyupbe.bodyupbe.dto.request.user;

import com.bodyupbe.bodyupbe.dto.request.community.PostDto;
import com.bodyupbe.bodyupbe.model.user.Role;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
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

    Set<PostDto> bookmarkPosts;
}
