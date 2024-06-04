package com.bodyupbe.bodyupbe.dto.request.workout_program;

import com.bodyupbe.bodyupbe.dto.request.community.PostDto;
import com.bodyupbe.bodyupbe.dto.request.user.UserChallengeDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FinishProgramTagDto {
    Integer id;
    PostDto postDto;
    UserChallengeDto userChallengeDto;
}
