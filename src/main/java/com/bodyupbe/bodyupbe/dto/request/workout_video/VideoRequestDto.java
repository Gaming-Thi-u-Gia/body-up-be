package com.bodyupbe.bodyupbe.dto.request.workout_video;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class VideoRequestDto {
    int id;
    String name;
    String url;
    boolean isFeatured;
}
