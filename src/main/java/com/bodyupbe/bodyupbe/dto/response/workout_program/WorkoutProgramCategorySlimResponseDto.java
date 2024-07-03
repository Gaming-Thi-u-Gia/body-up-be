package com.bodyupbe.bodyupbe.dto.response.workout_program;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@AllArgsConstructor
public class WorkoutProgramCategorySlimResponseDto {
    int id;
    String name;
    String type;
}
