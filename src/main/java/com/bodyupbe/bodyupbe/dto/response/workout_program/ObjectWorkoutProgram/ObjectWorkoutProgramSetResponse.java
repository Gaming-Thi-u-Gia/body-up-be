package com.bodyupbe.bodyupbe.dto.response.workout_program.ObjectWorkoutProgram;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ObjectWorkoutProgramSetResponse<T> {
    private Set<T> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean isLast;
}
