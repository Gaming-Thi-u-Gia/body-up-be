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

    VideoRepository videoRepository;
    UserRepository userRepository;

    public VideoBookmarkResponseSlim getBookmarkVideo(int userId, String url) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Video video = videoRepository.findVideoByUrl(url);

        boolean isBookmarked = videoRepository.findBookmarkByUserIdAndVideoId(userId, video.getUrl());

        if (isBookmarked) {
            user.getBookmarkVideos().remove(video);
            userRepository.save(user);
            return new VideoBookmarkResponseSlim(userId, url, false);
        } else {
            user.getBookmarkVideos().add(video);
            userRepository.save(user);
            return new VideoBookmarkResponseSlim(userId, url, true);
        }
    }
}
