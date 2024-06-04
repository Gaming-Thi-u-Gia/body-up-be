package com.bodyupbe.bodyupbe.dto.request.workout_video;

import com.bodyupbe.bodyupbe.dto.request.user.UserDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookmarkVideoDto {
    Integer id;
    UserDto userDto;
    VideoDto videoDto;
}
