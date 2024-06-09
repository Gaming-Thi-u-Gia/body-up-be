package com.bodyupbe.bodyupbe.model.recipe;

import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    double avgStar;
    @Column(name="prep_time")
    String prepTime;
    @Column(name = "cook_time")
    String cookTime;
    String img;
    @Column(name = "cook_detail")
    String cookDetail;

    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "rating-recipes")
    Set<RatingRecipe> ratingRecipes;

    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "ingredient-recipes")
    Set<IngredientRecipe> ingredientRecipes;

    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "step-recipes")
    Set<OtherImageRecipe> otherImageRecipes;

    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    @JsonManagedReference(value = "note-recipes")
    Set<NoteRecipe> noteRecipes;

    @ManyToMany(mappedBy = "bookmarkRecipes")
    @JsonBackReference(value = "bookmark-recipes")
    Set<User> bookmarkUsers;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_collection",
            joinColumns = @JoinColumn(name = "recipe_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id",referencedColumnName = "id")
    )
    @JsonManagedReference(value = "recipe-topics")
    Set<Topic> recipeTopics;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_filter",
            joinColumns = @JoinColumn(name = "recipe_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_category_id",referencedColumnName = "id")
    )
    @JsonManagedReference(value = "recipe-categories")
    Set<RecipeCategory> recipeCategories;
}
