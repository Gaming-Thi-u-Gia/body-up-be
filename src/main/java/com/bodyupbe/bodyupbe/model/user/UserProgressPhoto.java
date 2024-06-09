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
@Data
@Table(name = "user_progress_photos")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserProgressPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    boolean visibility;
    String caption;
    Date date;
    @Column(name = "photo_angle")
    String photoAngle;
    @Column(name = "create_at")
    Date createAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonBackReference(value = "user-progress-photos")
    User user;

}
