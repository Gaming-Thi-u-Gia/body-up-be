package com.bodyupbe.bodyupbe.model.recipe;

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
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    @Column(name = "average_rating")
    double averageRating;
    @Column(name="prep_time")
    String prepTime;
    @Column(name = "cook_time")
    String cookTime;
    String img;
    @Column(name = "cook_detail")
    String cookDetail;
    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    Set<RecipeCollection> recipeCollections;
    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    Set<RatingRecipe> ratingRecipes;
    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    Set<IngredientRecipe> ingredientRecipes;
    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    Set<OtherImageRecipe> otherImageRecipes;
    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    Set<RecipeFilter> recipeFilters;
    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    Set<BookmarkRecipe> bookmarkRecipes;
    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    Set<NoteRecipe> noteRecipes;

}
