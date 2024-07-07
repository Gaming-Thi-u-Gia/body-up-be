package com.bodyupbe.bodyupbe.dto.request.workout_video;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DailyVideoRequestDto {
    Integer id;
    VideoRequestDto video;
}
