package com.bodyupbe.bodyupbe.dto.response.workout_video;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Builder
@Getter
@Setter
public class VideoCategoryTableResponseDto {
    String type;
    Set<VideoCategorySlimResponseDto> videoCategories;
}
