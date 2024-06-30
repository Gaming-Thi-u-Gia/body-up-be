package com.bodyupbe.bodyupbe.service.daily_video;


import com.bodyupbe.bodyupbe.dto.mapper.daily_exercise.DailyVideoMapper;
import com.bodyupbe.bodyupbe.dto.response.workout_video.DailyVideoResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;

import com.bodyupbe.bodyupbe.model.workout_video.DailyVideo;
import com.bodyupbe.bodyupbe.repository.DailyExerciseRepository;
import com.bodyupbe.bodyupbe.repository.DailyVideoRepository;

import com.bodyupbe.bodyupbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DailyVideoService {
    DailyVideoRepository dailyVideoRepository;
    private final DailyVideoMapper dailyVideoMapper;
    private final UserRepository userRepository;

    //Get daily video by day
    public Set<DailyVideoResponseDto> getDailyVideoByDay(User user,String day,int workoutProgramId) {
        Set<DailyVideo> dailyVideos = dailyVideoRepository.findAllByUserIdAndDayAndWorkoutProgramId(user.getId(), day,workoutProgramId);
        return dailyVideoMapper.toListDailyVideoResponseDto(dailyVideos);
    }

    public void updateDailyVideoStatus(int dailyVideoId, int userId) {
        DailyVideo dailyVideo = dailyVideoRepository.findById(dailyVideoId).orElseThrow(() -> new IllegalArgumentException("DailyVideo not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Kiểm tra xem User có liên quan đến DailyVideo thông qua DailyExercise hay không
        boolean isUserRelated = user.getUserDailyChallenges().stream()
                .anyMatch(udc -> udc.getDailyExercise().getDailyViveos().contains(dailyVideo));

        if (isUserRelated) {
            dailyVideo.setStatus("complete");
            dailyVideoRepository.save(dailyVideo);
        } else {
            throw new IllegalArgumentException("User is not related to the DailyVideo");
        }
    }
}
