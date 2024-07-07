package com.bodyupbe.bodyupbe.dto.mapper.video;

import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoCategoryResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoCategorySlimResponseDto;
import com.bodyupbe.bodyupbe.model.workout_video.VideoCategory;
import com.bodyupbe.bodyupbe.repository.VideoCategoryRepository;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface VideoCategoryMapper {

    VideoCategory toVideoCategory(VideoCategoryRepository videoCategoryRequestDto);
    VideoCategoryResponseDto toVideoCategoryResponseDto(VideoCategory videoCategory);

    List<VideoCategoryResponseDto> toListVideoCategoryResponseDto(List<VideoCategory> videoCategories);

    Set<VideoCategoryResponseDto> toSetVideoCategory(Set<VideoCategory> videoCategories);

    VideoCategorySlimResponseDto toVideoCategorySlimResponseDto(VideoCategory videoCategory);
}
