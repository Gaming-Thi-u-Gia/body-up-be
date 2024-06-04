package com.bodyupbe.bodyupbe.model.workout_video;

import com.bodyupbe.bodyupbe.model.Topic;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "workout_video_collections")
public class WorkoutVideoCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name="topic_id",referencedColumnName = "id")
    @JsonBackReference
    Topic topic;
    @ManyToOne
    @JoinColumn(name="video_id",referencedColumnName = "id")
    @JsonBackReference
    Video video;
}
