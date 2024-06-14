package com.bodyupbe.bodyupbe.model.recipe;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "recipe_categorys")
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Column(length = 2000)
    String name;
    @Column(length = 2000)
    String type;
    String img;

    @ManyToMany(mappedBy = "recipeCategories")
    @JsonBackReference
    Set<Recipe> recipes;
}
