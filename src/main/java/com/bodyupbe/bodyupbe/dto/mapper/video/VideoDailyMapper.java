package com.bodyupbe.bodyupbe.dto.mapper.video;

import com.bodyupbe.bodyupbe.dto.response.workout_video.DailyVideoResponseDto;
import com.bodyupbe.bodyupbe.model.workout_video.DailyVideo;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface VideoDailyMapper {
     Set<DailyVideoResponseDto> dailyVideoToDailyVideoResponseDto(Set<DailyVideo> dailyVideo);
}
