package com.bodyupbe.bodyupbe.dto.request;

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
    Set<FinishProgramTagDto> finishProgramTagDtos;

}
