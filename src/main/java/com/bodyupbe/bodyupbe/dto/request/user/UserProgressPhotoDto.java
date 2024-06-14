package com.bodyupbe.bodyupbe.dto.request.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProgressPhotoDto {
    int id;
    boolean visibility;
    String caption;
    Date date;
    String photoAngle;
    Date createAt;
}
