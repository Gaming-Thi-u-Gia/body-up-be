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
@Table(name = "workout_program_categorys")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class WorkoutProgramCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @OneToMany(mappedBy = "workoutProgramCategory",cascade = CascadeType.ALL)
    Set<WorkoutProgramFilter> workoutProgramFilters;
}
