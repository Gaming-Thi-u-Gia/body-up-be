package com.bodyupbe.bodyupbe.model.workout_program;

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
@Table(name = "workout_program_filters")
public class WorkoutProgramFilter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name="workout_program_id",referencedColumnName = "id")
    @JsonBackReference
    WorkoutProgram workoutProgram;
    @ManyToOne
    @JoinColumn(name="workout_program_category_id",referencedColumnName = "id")
    @JsonBackReference
    WorkoutProgramCategory workoutProgramCategory;
}
