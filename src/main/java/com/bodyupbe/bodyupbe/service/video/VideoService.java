package com.bodyupbe.bodyupbe.service.video;

import com.bodyupbe.bodyupbe.dto.mapper.video.VideoMapper;
import com.bodyupbe.bodyupbe.dto.request.workout_video.VideoRequestDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoResponseDto;
import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.bodyupbe.bodyupbe.model.workout_video.VideoCategory;
import com.bodyupbe.bodyupbe.repository.TopicRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
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
public class VideoService {

    VideoMapper videoMapper;
    VideoRepository videoRepository;
    VideoCategoryRepository videoCategoryRepository;
    TopicRepository topicRepository;
    UserRepository userRepository;

    public VideoResponseDto createVideo(VideoRequestDto videoRequestDto) {
        Video video = videoMapper.toVideo(videoRequestDto);
        return videoMapper.toVideoResponseDto(videoRepository.save(video));
    }

    public List<VideoResponseDto> getVideoAll() {
        List<Video> videos = videoRepository.findAll();
        return videoMapper.toListVideoResponseDto(videos);
    }

    public VideoResponseDto getVideoById(int id) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new RuntimeException("Video not found"));
        return videoMapper.toVideoResponseDto(video);
    }

    public void deleteVideo(int id) {
        videoRepository.deleteById(id);
    }

    public VideoResponseDto updateVideo(VideoRequestDto videoRequestDto) {
        Video video = videoMapper.toVideo(videoRequestDto);
        return videoMapper.toVideoResponseDto(videoRepository.save(video));
    }

    public Set<VideoResponseDto> getVideoByCategory(int categoryId) {
        VideoCategory videoCategory = videoCategoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Category not found"));
        return videoMapper.toSetVideos(videoCategory.getVideos());
    }

    public Set<VideoResponseDto> getVideoByTopic(int topicId) {
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new RuntimeException("Topic not found"));
        return videoMapper.toSetVideos(topic.getVideos());
    }

    public List<VideoResponseDto> searchVideo(String name) {
        List<Video> videos = videoRepository.findByNameContainingIgnoreCase(name);
        return videoMapper.toListVideoResponseDto(videos);
    }

    public Set<VideoResponseDto> getAllBookmarkedVideo(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Set<Video> bookmarkedVideos = user.getBookmarkVideos();
        return videoMapper.toSetVideos(bookmarkedVideos);
    }

}
