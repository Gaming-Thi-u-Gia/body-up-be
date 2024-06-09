package com.bodyupbe.bodyupbe.dto.request.user;

import com.bodyupbe.bodyupbe.dto.request.community.PostDto;
import com.bodyupbe.bodyupbe.dto.request.workout_program.WorkoutProgramDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChallengeDto {
    int id;
    String status;
    UserDto userDto;
    WorkoutProgramDto workoutProgramDto;

    Set<PostDto> posts;

}
