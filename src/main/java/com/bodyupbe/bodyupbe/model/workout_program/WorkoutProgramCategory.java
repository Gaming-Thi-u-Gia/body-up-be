package com.bodyupbe.bodyupbe.model.workout_program;

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
@Table(name = "workout_program_categorys")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class WorkoutProgramCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 2000)
    String name;
    @Column(length = 2000)
    String type;

    @ManyToMany(mappedBy = "workoutProgramCategories")
    @JsonBackReference
    Set<WorkoutProgram> workoutPrograms;
}
