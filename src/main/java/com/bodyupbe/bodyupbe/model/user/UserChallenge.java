package com.bodyupbe.bodyupbe.model.user;

import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "user_challenges")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String status;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    @JsonBackReference
    User user;
    @ManyToOne
    @JoinColumn(name="workout_program_id",referencedColumnName = "id")
    @JsonBackReference
    WorkoutProgram workoutProgram;

    @JsonIgnore
    @ManyToMany(mappedBy = "userChallenges")
    Set<Post> posts;
}
