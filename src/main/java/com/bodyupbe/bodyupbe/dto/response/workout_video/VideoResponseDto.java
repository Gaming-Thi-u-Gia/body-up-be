package com.bodyupbe.bodyupbe.dto.response.workout_video;

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
public class VideoResponseDto {
    int id;
    String name;
    String url;
    boolean isFeatured;

    Set<VideoCategorySlimResponseDto> videoCategories;
}