package com.bodyupbe.bodyupbe.dto.response.user;

import com.bodyupbe.bodyupbe.dto.response.community.PostSlimResponse;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramSlimResponseDto;
import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserChallengeResponseDto {
    int id;
    String status;
    User user;
    WorkoutProgramResponseDto workoutProgram;
    Set<PostSlimResponse> posts;
}
