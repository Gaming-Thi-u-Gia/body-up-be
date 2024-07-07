package com.bodyupbe.bodyupbe.dto.mapper.daily_exercise;

import com.bodyupbe.bodyupbe.dto.response.workout_video.DailyVideoResponseDto;
import com.bodyupbe.bodyupbe.model.workout_video.DailyVideo;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface DailyVideoMapper {
    Set<DailyVideoResponseDto> toListDailyVideoResponseDto(Set<DailyVideo> dailyVideo);
}
