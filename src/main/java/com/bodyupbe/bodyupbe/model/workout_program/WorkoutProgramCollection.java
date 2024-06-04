package com.bodyupbe.bodyupbe.model.workout_program;

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
@Table(name = "workout_program_collections")
public class WorkoutProgramCollection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @ManyToOne
    @JoinColumn(name="workout_program_id",referencedColumnName = "id")
    @JsonBackReference
    WorkoutProgram workoutProgram;
    @ManyToOne
    @JoinColumn(name="topic_id",referencedColumnName = "id")
    @JsonBackReference
    Topic topic;
}
