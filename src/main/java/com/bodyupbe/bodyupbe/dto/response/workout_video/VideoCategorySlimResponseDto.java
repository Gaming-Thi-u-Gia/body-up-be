package com.bodyupbe.bodyupbe.dto.response.workout_video;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class VideoCategorySlimResponseDto {
    int id;
    String name;
    String type;
}