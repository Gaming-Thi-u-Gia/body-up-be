package com.bodyupbe.bodyupbe.dto.response.workout_video;

import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Set;
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class VideoBookmarkResponseSlim {
    int userId;
    String url;
    boolean isBookmarked;

}
