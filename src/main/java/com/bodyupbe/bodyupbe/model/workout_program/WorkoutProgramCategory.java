package com.bodyupbe.bodyupbe.model.workout_program;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "workout_program_categorys")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class WorkoutProgramCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String type;
    @ManyToMany(mappedBy = "workoutProgramCategories")
    Set<WorkoutProgram> workoutPrograms;
}
