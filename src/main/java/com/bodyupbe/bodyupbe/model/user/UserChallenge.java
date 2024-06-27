package com.bodyupbe.bodyupbe.model.user;

import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Table(name = "user_challenges")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class UserChallenge {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 2000)
    String status;
//    @CreationTimestamp
//    @Column(name = "create_at")
//    Date createAt;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    @JsonBackReference
    User user;

    @ManyToOne
    @JoinColumn(name="workout_program_id",referencedColumnName = "id")
    @JsonBackReference
    WorkoutProgram workoutProgram;

    @ManyToMany(mappedBy = "userChallenges")
    @JsonIgnore
    Set<Post> posts;

}
