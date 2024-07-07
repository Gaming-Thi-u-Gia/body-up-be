package com.bodyupbe.bodyupbe.dto.response.admin.dashboard;

import com.bodyupbe.bodyupbe.dto.response.workout_video.TopicVideoSlimResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoCategorySlimResponseDto;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class VideoResponseForAdminDto {
    int id;
    String name;
    String url;
    boolean isFeatured;
    List<TopicVideoSlimResponseDto> videoTopics;
    List<VideoCategorySlimResponseDto> videoCategories;
}