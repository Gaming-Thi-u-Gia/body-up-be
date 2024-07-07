package com.bodyupbe.bodyupbe.model;

import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "notifications")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(length = 2000)
    String message;
    @Column(name = "created_at")
    @CreationTimestamp
    Date createdAt;

    @ManyToMany(mappedBy = "notifications", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JsonBackReference
    Set<User> users;

    @OneToOne
    @JoinColumn(name = "workout_program_id", referencedColumnName = "id")
    @JsonBackReference
    WorkoutProgram workoutProgram;
}