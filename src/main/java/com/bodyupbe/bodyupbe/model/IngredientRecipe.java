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
@Table(name = "ingredient_recipes")
public class IngredientRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String amount;
    String name;
    @ManyToOne
    @JoinColumn(name="recipe_id",referencedColumnName = "id")
    @JsonBackReference
    Recipe recipe;
}