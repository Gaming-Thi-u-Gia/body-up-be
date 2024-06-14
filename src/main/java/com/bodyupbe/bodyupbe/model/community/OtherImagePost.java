package com.bodyupbe.bodyupbe.model.community;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "other_image_posts")
public class OtherImagePost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 2000)
    String img;

    @ManyToOne
    @JoinColumn(name="post_id",referencedColumnName = "id")
    @JsonBackReference
    Post post;
}
