package com.bodyupbe.bodyupbe.dto.request.community;


import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BadgeDto {
    Integer id;
    String name;
}
