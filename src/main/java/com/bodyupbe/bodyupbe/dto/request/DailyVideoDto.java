package com.bodyupbe.bodyupbe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyVideoDto {
    Integer id;
    String status;
    DailyExerciseDto dailyExerciseDto;
    VideoDto videoDto;
}
