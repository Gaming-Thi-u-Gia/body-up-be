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
@Table(name = "note_recipes")
public class NoteRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 2000)
    String detail;

    @ManyToOne
    @JoinColumn(name="recipe_id",referencedColumnName = "id")
    @JsonBackReference
    Recipe recipe;
}
