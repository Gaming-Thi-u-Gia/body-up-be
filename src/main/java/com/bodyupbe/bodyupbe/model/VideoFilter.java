package com.bodyupbe.bodyupbe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "video_filters")
public class VideoFilter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name="video_category_id",referencedColumnName = "id")
    @JsonBackReference
    VideoCategory videoCategory;
    @ManyToOne
    @JoinColumn(name="video_id",referencedColumnName = "id")
    @JsonBackReference
    Video video;
}
