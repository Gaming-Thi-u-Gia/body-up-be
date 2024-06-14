package com.bodyupbe.bodyupbe.dto.response;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicResponseDto {
    Integer id;
    String topic;
    String name;
}
