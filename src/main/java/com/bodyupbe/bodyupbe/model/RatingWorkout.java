package com.bodyupbe.bodyupbe.model;

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
@ToString
@Table(name = "rating_workout")
public class RatingWorkout {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    int star;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    FeedbackWorkout feedbackWorkout;
}