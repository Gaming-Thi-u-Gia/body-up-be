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
public class VideoSlimResponseDto {
    int id;
    String name;
    String url;
    boolean isFeatured;
}