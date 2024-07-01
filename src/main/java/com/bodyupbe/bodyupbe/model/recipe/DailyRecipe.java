package com.bodyupbe.bodyupbe.model.recipe;

import com.bodyupbe.bodyupbe.model.workout_video.DailyExercise;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "daily_recipes")
public class DailyRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name="recipe_id",referencedColumnName = "id")
    @JsonBackReference
    Recipe recipe;
    @ManyToOne
    @JoinColumn(name="daily_exercise_id",referencedColumnName = "id")
    @JsonBackReference
    DailyExercise dailyExercise;
    String part;
}
