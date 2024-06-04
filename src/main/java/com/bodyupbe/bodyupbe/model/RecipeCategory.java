package com.bodyupbe.bodyupbe.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "recipe_categorys")
public class RecipeCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String type;
    @OneToMany(mappedBy = "recipeCategory",cascade = CascadeType.ALL)
    Set<RecipeFilter> recipeFilters;
}
