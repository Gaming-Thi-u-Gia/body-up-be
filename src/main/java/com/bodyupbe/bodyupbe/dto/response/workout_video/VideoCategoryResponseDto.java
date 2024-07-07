package com.bodyupbe.bodyupbe.dto.response.workout_video;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoCategoryResponseDto {
    int id;
    String name;
    String type;
    Set<VideoSlimResponseDto> videos;
    Set<TopicDto> videoTopics;
}
