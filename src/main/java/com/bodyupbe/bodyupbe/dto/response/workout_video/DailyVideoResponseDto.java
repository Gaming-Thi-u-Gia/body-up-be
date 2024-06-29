package com.bodyupbe.bodyupbe.dto.response.workout_video;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class DailyVideoResponseDto {
    int id;
    String status;
    String day;
    VideoSlimResponseDto video;
}
