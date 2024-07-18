package com.bodyupbe.bodyupbe.model;

import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "feedback_workout")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FeedbackWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(length = 2000)
    String feedback;

    @Column(name = "created_at")
    @CreationTimestamp
    Date createdAt;

    @OneToOne(mappedBy = "feedbackWorkout", cascade = CascadeType.ALL)
    @JoinColumn(name="rating_workout_id",referencedColumnName = "id")
    @JsonManagedReference
    RatingWorkout ratingWorkout = new RatingWorkout();

    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    @JsonBackReference
    User user;

    @ManyToOne
    @JoinColumn(name="workout_program_id",referencedColumnName = "id")
    @JsonBackReference
    WorkoutProgram workoutProgram;


}