package com.bodyupbe.bodyupbe.dto.response.admin.dashboard;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class VideoCardResponseForAdminDto {
    int id;
    String name;
    String url;
    boolean isFeatured;
}
