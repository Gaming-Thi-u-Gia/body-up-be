package com.bodyupbe.bodyupbe.model.workout_program;

import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.user.UserChallenge;
import com.bodyupbe.bodyupbe.model.workout_video.DailyExercise;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Table(name = "workout_progams")
@FieldDefaults(level= AccessLevel.PRIVATE)
public class WorkoutProgram {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    String type;
    String equipment;
    String detail;
    String day;
    String time;
    String year;
    @Column(name = "release_date")
    Date releaseDate;
    @OneToMany(mappedBy = "workoutProgram",cascade = CascadeType.ALL)
    Set<DailyExercise> dailyExercises;

    @OneToMany(mappedBy = "workoutProgram",cascade = CascadeType.ALL)
    Set<UserChallenge> userChallenges;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "workout_program_collection",
            joinColumns = @JoinColumn(name = "workout_program_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id",referencedColumnName = "id")
    )
    Set<Topic> programTopics;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "workout_program_filter",
            joinColumns = @JoinColumn(name = "workout_program_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "workout_program_category_id",referencedColumnName = "id")
    )
    Set<WorkoutProgramCategory> workoutProgramCategories;
}
