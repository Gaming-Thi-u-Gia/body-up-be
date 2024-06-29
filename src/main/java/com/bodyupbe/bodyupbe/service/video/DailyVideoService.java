package com.bodyupbe.bodyupbe.service.video;

import com.bodyupbe.bodyupbe.dto.mapper.video.VideoDailyMapper;
import com.bodyupbe.bodyupbe.dto.response.workout_video.DailyVideoResponseDto;
import com.bodyupbe.bodyupbe.model.workout_video.DailyVideo;
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
    private final DailyVideoRepository dailyVideoRepository;
    VideoDailyMapper videoDailyMapper;

    public Set<DailyVideoResponseDto> getVideosByDay(String day, Integer workoutProgramId) {
        Set<DailyVideo> videos = dailyVideoRepository.findAllByDayAndWorkoutProgramId(day, workoutProgramId);
        return videoDailyMapper.dailyVideoToDailyVideoResponseDto(videos);
    }
}
