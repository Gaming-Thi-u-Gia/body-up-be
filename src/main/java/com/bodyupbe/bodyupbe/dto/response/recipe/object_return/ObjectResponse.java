package com.bodyupbe.bodyupbe.dto.response.recipe.object_return;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ObjectResponse<T> {
    private T content;
    private int pageNo;
    private int pageSize;
    private long totalElements;
    private long totalPages;
    private boolean isLast;
}