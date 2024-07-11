package com.bodyupbe.bodyupbe.service.video;

import com.bodyupbe.bodyupbe.dto.response.workout_video.VideoBookmarkResponseSlim;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.repository.VideoRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookmarkService {

    VideoRepository videoRepository;
    UserRepository userRepository;

    @Transactional
    public VideoBookmarkResponseSlim getBookmarkVideo(int userId, int id) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        boolean bookmarked;
        if (user.getBookmarkVideos().contains(video)) {
            removeBookmarkVideo(user, video);
            bookmarked = false;
        } else {
            addBookmarkVideo(user, video);
            bookmarked = true;
        }

        userRepository.save(user);
        return new VideoBookmarkResponseSlim(id, userId, bookmarked);
    }

    private void addBookmarkVideo(User user, Video video) {
        user.getBookmarkVideos().add(video);
        video.getBookmarkUsers().add(user);
    }

    private void removeBookmarkVideo(User user, Video video) {
        user.getBookmarkVideos().remove(video);
        video.getBookmarkUsers().remove(user);
    }
}
