package com.bodyupbe.bodyupbe.model.workout_video;

import com.bodyupbe.bodyupbe.model.recipe.DailyRecipe;
import com.bodyupbe.bodyupbe.model.user.UserDailyChallenge;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "daily_exercises")
public class DailyExercise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String day;
    @ManyToOne
    @JoinColumn(name="workout_program_id",referencedColumnName = "id")
    @JsonBackReference
    WorkoutProgram workoutProgram;

    @OneToMany(mappedBy = "dailyExercise",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<DailyVideo> dailyVideos;

    @OneToMany(mappedBy = "dailyExercise",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<UserDailyChallenge> userDailyChallenges;
    @OneToMany(mappedBy = "dailyExercise",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<DailyRecipe> dailyRecipes;
}