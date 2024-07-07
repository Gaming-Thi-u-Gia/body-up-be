package com.bodyupbe.bodyupbe.dto.response.workout_video.ObjectVideo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjectVideoSetResponse<T> {
    private Set<T> content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean isLast;
}
