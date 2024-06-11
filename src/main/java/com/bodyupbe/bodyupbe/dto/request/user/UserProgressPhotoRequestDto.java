package com.bodyupbe.bodyupbe.dto.request.user;

import com.bodyupbe.bodyupbe.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserProgressPhotoRequestDto {
    int id;
    boolean visibility;
    String caption;
    Date date;
    String photoAngle;
    Date createAt;
    String imgUrl;
    UserRequestDto user;
}
