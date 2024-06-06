package com.bodyupbe.bodyupbe.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ObjResponse<T> {
    List<T> content;
    int pageNo;
    int pageSize;
    long totalElements;
    long totalPages;
    boolean isLast;
}
