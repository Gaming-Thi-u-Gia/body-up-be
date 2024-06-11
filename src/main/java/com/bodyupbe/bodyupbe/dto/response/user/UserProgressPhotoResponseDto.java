package com.bodyupbe.bodyupbe.dto.response.user;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProgressPhotoResponseDto {
    int id;
    boolean visibility;
    String caption;
    Date date;
    String photoAngle;
    Date createAt;
    String imgUrl;
    UserSlimResponseDto user;
}
