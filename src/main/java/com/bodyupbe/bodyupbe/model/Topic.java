package com.bodyupbe.bodyupbe.model;

import com.bodyupbe.bodyupbe.model.recipe.RecipeCollection;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgramCollection;
import com.bodyupbe.bodyupbe.model.workout_video.WorkoutVideoCollection;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "topics")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String topic;
    String name;
    @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL)
    Set<WorkoutProgramCollection> workoutProgramCollections;
    @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL)
    Set<WorkoutVideoCollection> workoutVideoCollections;
    @OneToMany(mappedBy = "topic",cascade = CascadeType.ALL)
    Set<RecipeCollection> recipeCollections;
}
