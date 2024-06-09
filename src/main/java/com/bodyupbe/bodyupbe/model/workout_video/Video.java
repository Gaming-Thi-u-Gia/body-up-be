package com.bodyupbe.bodyupbe.model.workout_video;

import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToMany(mappedBy = "bookmarkVideos")
    Set<User> bookmarkUsers;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "workout_video_collection",
            joinColumns = @JoinColumn(name = "video_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id",referencedColumnName = "id")
    )
    Set<Topic> videoTopics;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "video_filter",
            joinColumns = @JoinColumn(name = "video_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "video_category_id",referencedColumnName = "id")
    )
    Set<VideoCategory> videoCategories;
}
