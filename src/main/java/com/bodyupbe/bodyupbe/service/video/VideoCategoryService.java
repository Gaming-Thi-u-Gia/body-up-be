package com.bodyupbe.bodyupbe.service.video;

import com.bodyupbe.bodyupbe.dto.mapper.video.VideoCategoryMapper;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoCategoryResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoResponseDto;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.bodyupbe.bodyupbe.model.workout_video.VideoCategory;
import com.bodyupbe.bodyupbe.repository.VideoCategoryRepository;
import com.bodyupbe.bodyupbe.repository.VideoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class VideoCategoryService {
    VideoCategoryMapper videoCategoryMapper;
    VideoCategoryRepository videoCategoryRepository;
    VideoRepository videoRepository;
    public List<VideoCategoryResponseDto> getAllVideoCategory() {
        List<VideoCategory> videoCategories = videoCategoryRepository.findAll();
        return videoCategoryMapper.toListVideoCategoryResponseDto(videoCategories);
    }

    public VideoCategoryResponseDto getVideoCategoryById(int id) {
        VideoCategory videoCategory = videoCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Video Category not found"));
        return videoCategoryMapper.toVideoCategoryResponseDto(videoCategory);
    }

    public Set<VideoCategoryResponseDto> getCategoryByVideo(int videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Category not found"));
        return videoCategoryMapper.toSetVideoCategory(video.getVideoCategories());
    }
}
