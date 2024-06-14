package com.bodyupbe.bodyupbe.model.workout_video;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "video_categorys")
public class VideoCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 2000)
    String name;
    @Column(length = 2000)
    String type;

    @ManyToMany(mappedBy = "videoCategories")
    @JsonBackReference
    Set<Video> videos;
}
