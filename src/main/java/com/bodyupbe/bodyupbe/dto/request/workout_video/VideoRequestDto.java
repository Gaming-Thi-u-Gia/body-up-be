package com.bodyupbe.bodyupbe.dto.request.workout_video;


import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoCategoryResponseDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoRequestDto {
    int id;
    String name;
    String url;
    boolean isFeatured;
    Set<TopicDto> videoTopics;
    Set<VideoCategoryRequestDto> videoCategories;
}
