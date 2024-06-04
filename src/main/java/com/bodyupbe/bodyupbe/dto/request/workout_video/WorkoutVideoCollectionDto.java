package com.bodyupbe.bodyupbe.dto.request.workout_video;

import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutVideoCollectionDto {
    int id;
    TopicDto topicDto;
    VideoDto videoDto;
}
