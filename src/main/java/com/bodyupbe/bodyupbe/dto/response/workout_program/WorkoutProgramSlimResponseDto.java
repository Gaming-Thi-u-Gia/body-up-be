package com.bodyupbe.bodyupbe.dto.response.workout_program;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;


@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
public class WorkoutProgramSlimResponseDto {
    int id;
    String name;
    String type;
    String equipment;
    String detail;
    String day;
    String time;
    String year;
    Date releaseDate;
}
