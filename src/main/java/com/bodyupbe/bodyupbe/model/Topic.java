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
    Set<RecipesCollection> recipesCollections;
}
