package com.bodyupbe.bodyupbe.model.recipe;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "other_image_recipes")
public class OtherImageRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 50000)
    String img;

    @ManyToOne
    @JoinColumn(name="recipe_id",referencedColumnName = "id")
    @JsonBackReference
    Recipe recipe;
}
