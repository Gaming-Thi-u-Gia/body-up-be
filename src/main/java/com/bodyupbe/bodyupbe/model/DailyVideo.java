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
@Table(name = "daily_videos")
public class DailyVideo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String status;
    @ManyToOne
    @JoinColumn(name="daily_exercise_id",referencedColumnName = "id")
    @JsonBackReference
    DailyExercise dailyExercise;
    @ManyToOne
    @JoinColumn(name="video_id",referencedColumnName = "id")
    @JsonBackReference
    Video video;
}
