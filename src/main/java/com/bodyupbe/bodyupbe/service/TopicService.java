package com.bodyupbe.bodyupbe.service;

import com.bodyupbe.bodyupbe.dto.mapper.TopicMapper;
import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.response.TopicResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.TopicWorkoutProgramResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.TopicVideoResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoSlimResponseDto;
import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.bodyupbe.bodyupbe.repository.TopicRepository;
import com.bodyupbe.bodyupbe.repository.VideoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TopicService {
    TopicMapper topicMapper;
    TopicRepository topicRepository;
    VideoRepository videoRepository;
    public List<TopicResponseDto> getAllTopic() {
        List<Topic> topics = topicRepository.findAll();
        return topicMapper.toListTopicResponseDto(topics);
    }

    public TopicResponseDto getTopicById(int id) {
        Topic topic = topicRepository.findById(id).orElseThrow(() -> new RuntimeException("Topic not found"));
        return topicMapper.toTopicResponseDto(topic);
    }

    public TopicResponseDto createTopic(TopicDto topicDto) {
        Topic topic = topicMapper.toTopic(topicDto);
        return topicMapper.toTopicResponseDto(topicRepository.save(topic));
    }

    public TopicResponseDto updateTopic(TopicDto topicDto) {
        Topic topic = topicMapper.toTopic(topicDto);
        return topicMapper.toTopicResponseDto(topicRepository.save(topic));
    }

    public void deleteTopic(int id) {
        topicRepository.deleteById(id);
    }

    public Set<TopicResponseDto> getTopicByVideoId(int videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new RuntimeException("Video not found"));
        return topicMapper.toSetTopicResponseDto(video.getVideoTopics());
    }

    public Set<TopicResponseDto> getTopicForVideo() {
        Set<Topic> topics = topicRepository.findTopicsByTopic("workout-video");
        return topicMapper.toSetTopicResponseDto(topics);
    }

    public Set<TopicResponseDto> getTopicForWourkoutProgram() {
        Set<Topic> topics = topicRepository.findTopicsByTopic("workout-program");
        return topicMapper.toSetTopicResponseDto(topics);
    }

    public Set<TopicWorkoutProgramResponseDto> getTopicWithWorkoutProgram() {
        Set<Topic> topics = topicRepository.findTopicsByTopic("workout-program");
        return topicMapper.toTopicWorkoutProgram(topics);
    }

    public Set<TopicWorkoutProgramResponseDto> getTopicWithWorkoutProgramById(int topicId) {
        Set<Topic> topics = topicRepository.findTopicsById(topicId);
        return topicMapper.toTopicWorkoutProgram(topics);
    }

    public Set<TopicVideoResponseDto> getTopicWithWorkoutVideo(Optional<User> user) {
        Set<Topic> topics = topicRepository.findTopicsByTopic("workout-video");
        Set<TopicVideoResponseDto> setTopicVideoResponseDto = topicMapper.toTopicVideo(topics);
        if (user.isPresent()) {
            for (TopicVideoResponseDto topicVideoResponseDto : setTopicVideoResponseDto) {
                for (VideoSlimResponseDto videoSlimResponseDto : topicVideoResponseDto.getVideos()) {
                    videoSlimResponseDto.setBookmarked(videoRepository.findBookmarkByUserIdAndVideoId(user.get().getId(), videoSlimResponseDto.getUrl()));
                }
            }
        }
        return setTopicVideoResponseDto;
    }
}
