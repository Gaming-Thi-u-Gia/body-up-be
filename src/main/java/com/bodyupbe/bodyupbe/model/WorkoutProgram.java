package com.bodyupbe.bodyupbe.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "workout_progams")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class WorkoutProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String type;
    String equipment;
    String detail;
    String day;
    String time;
    String year;
    @Column(name = "release_date")
    Date releaseDate;
    @OneToMany(mappedBy = "workoutProgram",cascade = CascadeType.ALL)
    Set<DailyExercise> dailyExercises;
    @OneToMany(mappedBy = "workoutProgram",cascade = CascadeType.ALL)
    Set<WorkoutProgramFilter> workoutProgramFilters;
    @OneToMany(mappedBy = "workoutProgram",cascade = CascadeType.ALL)
    Set<WorkoutProgramCollection> workoutProgramCollections;
    @OneToMany(mappedBy = "workoutProgram",cascade = CascadeType.ALL)
    Set<UserChallenge> userChallenges;
}
