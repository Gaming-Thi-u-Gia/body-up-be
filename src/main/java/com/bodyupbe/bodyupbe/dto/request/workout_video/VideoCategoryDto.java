package com.bodyupbe.bodyupbe.dto.request.workout_video;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoCategoryDto {
    int id;
    String name;
    String type;
    Set<VideoFilterDto> videoFilterDtos;
}
