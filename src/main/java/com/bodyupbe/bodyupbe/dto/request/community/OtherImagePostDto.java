package com.bodyupbe.bodyupbe.dto.request.community;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OtherImagePostDto {
    Integer id;
    String img;
    PostDto post;
}
