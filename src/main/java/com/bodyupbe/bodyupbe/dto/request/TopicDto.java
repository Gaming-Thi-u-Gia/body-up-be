package com.bodyupbe.bodyupbe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TopicDto {
    Integer id;
    String topic;
    String name;
    String description;

    public TopicDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
