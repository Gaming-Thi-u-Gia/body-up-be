package com.bodyupbe.bodyupbe.dto.request.workout_video;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.request.user.UserDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoDto {
    int id;
    String name;
    String url;
    boolean isFeatured;
    Set<DailyVideoDto> dailyVideoDtos;

    Set<UserDto> bookmarkUsers;
    Set<TopicDto> videoTopics;
    Set<VideoCategoryDto> videoCategories;
}
