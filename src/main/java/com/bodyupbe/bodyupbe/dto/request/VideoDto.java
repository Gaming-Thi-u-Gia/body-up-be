package com.bodyupbe.bodyupbe.dto.request;

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
    Set<VideoFilterDto> videoFilterDtos;
    Set<BookmarkVideoDto> bookmarkVideoDtos;
    Set<WorkoutVideoCollectionDto> workoutVideoCollectionDtos;
}
