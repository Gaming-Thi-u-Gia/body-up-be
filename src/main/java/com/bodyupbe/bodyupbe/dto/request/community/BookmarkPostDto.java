package com.bodyupbe.bodyupbe.dto.request.community;

import com.bodyupbe.bodyupbe.dto.request.user.UserDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookmarkPostDto {
    Integer id;
    PostDto post;
    UserDto user;
}
