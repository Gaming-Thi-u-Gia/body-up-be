package com.bodyupbe.bodyupbe.dto.response.workout_video;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.VideoCategoryRequestDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AddWorkoutVideoResponseDto {
    int id;
    String name;
    String url;
    boolean isFeatured;
    Set<TopicDto> videoTopics;
    Set<VideoCategoryRequestDto> videoCategories;
}
