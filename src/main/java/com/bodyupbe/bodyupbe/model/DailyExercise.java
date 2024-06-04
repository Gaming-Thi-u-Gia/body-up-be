package com.bodyupbe.bodyupbe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "daily_exercises")
public class DailyExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name="workout_program_id",referencedColumnName = "id")
    @JsonBackReference
    WorkoutProgram workoutProgram;
    @OneToMany(mappedBy = "dailyExercise",cascade = CascadeType.ALL)
    Set<DailyVideo> dailyViveos;
    @OneToMany(mappedBy = "dailyExercise",cascade = CascadeType.ALL)
    Set<UserDailyChallenge> userDailyChallenges;
}
