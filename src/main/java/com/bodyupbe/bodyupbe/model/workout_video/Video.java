package com.bodyupbe.bodyupbe.model.workout_video;

import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "videos")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class Video {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 2000)
    String name;
    @Column(length = 2000)
    String url;
    @Column(name = "is_featured")
    boolean isFeatured;

    @OneToMany(mappedBy = "video",cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    Set<DailyVideo> dailyVideos;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "bookmark_videos",
            inverseJoinColumns = @JoinColumn(name = "user_id",referencedColumnName = "id"),
            joinColumns  = @JoinColumn(name = "video_id",referencedColumnName = "id")
    )
    @JsonManagedReference
    Set<User> bookmarkUsers;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "workout_video_collection",
            joinColumns = @JoinColumn(name = "video_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id",referencedColumnName = "id")
    )
    @JsonManagedReference
    Set<Topic> videoTopics;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "video_filter",
            joinColumns = @JoinColumn(name = "video_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "video_category_id",referencedColumnName = "id")
    )
    @JsonManagedReference
    Set<VideoCategory> videoCategories;
}
