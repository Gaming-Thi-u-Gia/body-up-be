package com.bodyupbe.bodyupbe.model.workout_video;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "video_categorys")
public class VideoCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String type;

    @ManyToMany(mappedBy = "videoCategories")
    Set<Video> videos;
}
