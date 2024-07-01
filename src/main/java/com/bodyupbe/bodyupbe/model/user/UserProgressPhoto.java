package com.bodyupbe.bodyupbe.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "user_progress_photos")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserProgressPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    boolean visibility;
    @Column(length = 2000)
    String caption;
    Date date;
    @Column(name = "img_url")
    String imgUrl;
    @Column(name = "photo_angle")
    String photoAngle;
    @Column(name = "create_at")
    Date createAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference
    User user;

}
