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
    @Column(length = 2000)
    String topic;
    @Column(length = 2000)
    String name;
    @Column(length = 2000)
    String description;

    @ManyToMany(mappedBy = "videoTopics", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonBackReference
    Set<Video> videos;

    @ManyToMany(mappedBy = "programTopics", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonBackReference
    Set<WorkoutProgram> workoutPrograms;

    @ManyToMany(mappedBy = "recipeTopics", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonBackReference
    Set<Recipe> recipes;
}
