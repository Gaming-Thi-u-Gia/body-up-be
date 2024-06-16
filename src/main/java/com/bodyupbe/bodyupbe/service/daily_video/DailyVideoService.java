package com.bodyupbe.bodyupbe.service.daily_video;


import com.bodyupbe.bodyupbe.dto.mapper.daily_exercise.DailyVideoMapper;
import com.bodyupbe.bodyupbe.dto.response.workout_video.DailyVideoResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;

import com.bodyupbe.bodyupbe.model.workout_video.DailyVideo;
import com.bodyupbe.bodyupbe.repository.DailyExerciseRepository;
import com.bodyupbe.bodyupbe.repository.DailyVideoRepository;

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

    //Get daily video by day
    public Set<DailyVideoResponseDto> getDailyVideoByDay(User user,String day) {
        Set<DailyVideo> dailyVideos = dailyVideoRepository.findAllByUserIdAndDay(user.getId(), day);
        return dailyVideoMapper.toListDailyVideoResponseDto(dailyVideos);
    }
}
