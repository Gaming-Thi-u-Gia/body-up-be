package com.bodyupbe.bodyupbe.service.video;

import com.bodyupbe.bodyupbe.dto.mapper.user.UserMapper;
import com.bodyupbe.bodyupbe.dto.mapper.video.VideoMapper;
import com.bodyupbe.bodyupbe.dto.response.user.UserResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoBookmarkResponseSlim;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.repository.VideoRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookmarkService {
    UserRepository userRepository;
    VideoRepository videoRepository;
    UserMapper userMapper;
    VideoMapper videoMapper;
    public Optional<UserResponseDto> setBookmarkVideo(int userId, int videoId) {
        Video video = videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Video not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(user.getBookmarkVideos().contains(video)) {
            user.getBookmarkRecipes().remove(video);
            video.getBookmarkUsers().remove(user);
            return Optional.of(userMapper.toUserResponseDto(userRepository.save(user)));
        }

        user.getBookmarkVideos().add(video);
        video.getBookmarkUsers().add(user);
        return Optional.of(userMapper.toUserResponseDto(userRepository.save(user)));
    }

    public Set<VideoBookmarkResponseSlim> getSetBookmarkVideoByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return videoMapper.toSetVideoBookmark(user.getBookmarkVideos());
    }
}
