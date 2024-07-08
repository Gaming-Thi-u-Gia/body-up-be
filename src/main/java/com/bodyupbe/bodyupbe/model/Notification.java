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
    import java.util.HashSet;
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

        @OneToOne(mappedBy = "notification")
        @JsonBackReference
        WorkoutProgram workoutProgram;

        @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
        @JoinTable(
                name = "user_notification",
                joinColumns = @JoinColumn(name = "user_id" , referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "notification_id", referencedColumnName = "id"))
        @JsonManagedReference
        Set<User> users = new HashSet<>();
    }