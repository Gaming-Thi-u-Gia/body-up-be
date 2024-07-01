package com.bodyupbe.bodyupbe.dto.request.user;


import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserChallengeRequestDto {
    int id;
    String status;

    User user;

    WorkoutProgram workoutProgram;

    Set<Post> posts;
}
