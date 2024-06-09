package com.bodyupbe.bodyupbe.model;

import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.fasterxml.jackson.annotation.JsonBackReference;
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
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String topic;
    String name;

    @ManyToMany(mappedBy = "videoTopics")
    @JsonBackReference
    Set<Video> videos;

    @ManyToMany(mappedBy = "programTopics")
    @JsonBackReference
    Set<WorkoutProgram> workoutPrograms;

    @ManyToMany(mappedBy = "recipeTopics")
    @JsonBackReference
    Set<Recipe> recipes;
}
