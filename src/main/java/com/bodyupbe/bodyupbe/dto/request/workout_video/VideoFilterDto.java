package com.bodyupbe.bodyupbe.dto.request.workout_video;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoFilterDto {
    int id;
    VideoCategoryDto videoCategoryDto;
    VideoDto videoDto;
}
