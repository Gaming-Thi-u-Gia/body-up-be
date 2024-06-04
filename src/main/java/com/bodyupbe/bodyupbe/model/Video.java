package com.bodyupbe.bodyupbe.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "videos")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String url;
    @Column(name = "is_featured")
    boolean isFeatured;
    @OneToMany(mappedBy = "video",cascade = CascadeType.ALL)
    Set<DailyVideo> dailyVideos;
    @OneToMany(mappedBy = "video",cascade = CascadeType.ALL)
    Set<VideoFilter> videoFilters;
    @OneToMany(mappedBy = "video",cascade = CascadeType.ALL)
    Set<BookmarkVideo> bookmarkVideos;
    @OneToMany(mappedBy = "video",cascade = CascadeType.ALL)
    Set<WorkoutVideoCollection> workoutVideoCollections;
}
