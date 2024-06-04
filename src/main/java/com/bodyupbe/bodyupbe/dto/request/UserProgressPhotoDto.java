package com.bodyupbe.bodyupbe.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProgressPhotoDto {
    int id;
    boolean visibility;
    String caption;
    Date date;
    String photoAngle;
    Date createAt;
    UserDto userDto;

}
