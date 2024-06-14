package com.bodyupbe.bodyupbe.dto.request.workout_program;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WorkoutProgramCategoryRequestDto {
    int id;
    String name;
    String type;
}
