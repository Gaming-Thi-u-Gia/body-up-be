package com.bodyupbe.bodyupbe.dto.response.workout_video;

import com.bodyupbe.bodyupbe.dto.request.workout_video.VideoCategoryRequestDto;
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
public class TopicVideoResponseDto {
    Integer id;
    String topic;
    String name;
    String description;

    Set<VideoSlimResponseDto> videos;
}
