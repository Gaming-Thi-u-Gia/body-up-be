package com.bodyupbe.bodyupbe.dto.request;

import com.bodyupbe.bodyupbe.dto.response.RatingWorkoutSlimResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedbackRequestDto {
    String feedback;
    Date createdAt;
    RatingWorkoutSlimResponseDto ratingWorkout;
    UserSlimResponseDto user;
}
