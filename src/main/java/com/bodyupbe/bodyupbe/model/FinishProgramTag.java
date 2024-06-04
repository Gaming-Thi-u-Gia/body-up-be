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
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "finish_program_tags")
public class FinishProgramTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name="post_id",referencedColumnName = "id")
    @JsonBackReference
    Post post;
    @ManyToOne
    @JoinColumn(name="user_challenge_id",referencedColumnName = "id")
    @JsonBackReference
    UserChallenge userChallenge;
}
