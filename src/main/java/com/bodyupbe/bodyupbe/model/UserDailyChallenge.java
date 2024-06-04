package com.bodyupbe.bodyupbe.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "user_daily_challenges")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserDailyChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String status;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    @JsonBackReference
    User user;
    @ManyToOne
    @JoinColumn(name="daily_exercise_id",referencedColumnName = "id")
    @JsonBackReference
    DailyExercise dailyExercise;
}
