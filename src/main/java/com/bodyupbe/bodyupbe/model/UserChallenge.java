package com.bodyupbe.bodyupbe.model;

import com.bodyupbe.bodyupbe.model.User;
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
    @OneToMany(mappedBy = "userChallenge",cascade = CascadeType.ALL)
    Set<FinishProgramTag> finishProgramTags;

}
