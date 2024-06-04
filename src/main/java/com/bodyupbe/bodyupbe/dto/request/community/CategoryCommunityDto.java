package com.bodyupbe.bodyupbe.dto.request.community;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CategoryCommunityDto {
    Integer id;
    Set<PostDto> postDtos;
}
