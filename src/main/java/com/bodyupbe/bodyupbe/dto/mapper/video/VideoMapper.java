package com.bodyupbe.bodyupbe.dto.mapper.video;

import com.bodyupbe.bodyupbe.dto.request.workout_video.VideoRequestDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoBookmarkResponseSlim;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoSlimResponseDto;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface VideoMapper {
    Video toVideo(VideoRequestDto videoRequestDto);

    VideoResponseDto toVideoResponseDto(Video video);

    List<VideoResponseDto> toListVideoResponseDto(List<Video> videos);
    VideoSlimResponseDto toVideoSlimResponseDto(Video video);

    Set<VideoResponseDto> toSetVideos(Set<Video> videoRequestDtoList);

    Set<VideoBookmarkResponseSlim> toSetVideoBookmark(Set<Video> videos);
}
