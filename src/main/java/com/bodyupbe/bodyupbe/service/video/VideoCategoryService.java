package com.bodyupbe.bodyupbe.service.video;

import com.bodyupbe.bodyupbe.dto.mapper.video.VideoCategoryMapper;
import com.bodyupbe.bodyupbe.dto.mapper.video.VideoMapper;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.ObjectVideo.ObjectVideoSetResponse;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoCategoryResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoCategorySlimResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoCategoryTableResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoSlimResponseDto;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.bodyupbe.bodyupbe.model.workout_video.VideoCategory;
import com.bodyupbe.bodyupbe.repository.VideoCategoryRepository;
import com.bodyupbe.bodyupbe.repository.VideoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VideoCategoryService {
    VideoCategoryMapper videoCategoryMapper;
    VideoCategoryRepository videoCategoryRepository;
    VideoRepository videoRepository;
    VideoMapper videoMapper;
    public List<VideoCategoryResponseDto> getAllVideoCategory() {
        List<VideoCategory> videoCategories = videoCategoryRepository.findAll();
        return videoCategoryMapper.toListVideoCategoryResponseDto(videoCategories);
    }

    public VideoCategoryResponseDto getVideoCategoryById(int id) {
        VideoCategory videoCategory = videoCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Video Category not found"));
        return videoCategoryMapper.toVideoCategoryResponseDto(videoCategory);
    }

    public Set<VideoCategoryResponseDto> getCategoryByVideo(int videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Cate" +
                "gory not found"));
        return videoCategoryMapper.toSetVideoCategory(video.getVideoCategories());
    }

    public Set<VideoCategoryTableResponseDto> getAllVideoCategoriesWithEachType() {
        List<VideoCategory> categories = videoCategoryRepository.findAll();
        List<VideoCategorySlimResponseDto> categoryDtoList = categories.stream()
                .map(category -> videoCategoryMapper.toVideoCategorySlimResponseDto(category))
                .collect(Collectors.toList());

        Map<String, Set<VideoCategorySlimResponseDto>> groupedByType = categoryDtoList.stream()
                .collect(Collectors.groupingBy(
                        VideoCategorySlimResponseDto::getType,
                        Collectors.toSet()
                ));

        Set<VideoCategoryTableResponseDto> responseDtoSet = groupedByType.entrySet().stream()
                .map(entry -> VideoCategoryTableResponseDto.builder()
                        .type(entry.getKey())
                        .videoCategories(entry.getValue())
                        .build())
                .collect(Collectors.toSet());

        return responseDtoSet;
    }

    public ObjectVideoSetResponse<VideoSlimResponseDto> getVideoByCategory(Set<Integer> categoryIds, Optional<Integer> userId, int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Video> pages = videoRepository.findVideosByCategoryIds(categoryIds, categoryIds.size(), pageable);
        List<Video> videos = pages.getContent();
        Set<VideoSlimResponseDto> setVideoSlimResponseDto = videoMapper.toSetVideoSlim(videos);
        if (userId.isPresent()) {
            for (VideoSlimResponseDto video : setVideoSlimResponseDto) {
                video.setBookmarked(videoRepository.findBookmarkByUserIdAndVideoId(userId.get(), video.getUrl()));
            }
        }

        ObjectVideoSetResponse<VideoSlimResponseDto> response = new ObjectVideoSetResponse<>();
        response.setContent(setVideoSlimResponseDto);
        response.setTotalElements(pages.getTotalElements());
        response.setTotalPages(pages.getTotalPages());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());
        return response;
    }



}
