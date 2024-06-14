package com.bodyupbe.bodyupbe.dto.request.workout_video;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoCategoryRequestDto {
    int id;
    String name;
    String type;
}
