package com.bodyupbe.bodyupbe.model.recipe;

import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.user.User;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level= AccessLevel.PRIVATE)
@Table(name = "recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    double avgStar;
    @Column(name="prep_time")
    int prepTime;
    @Column(name = "cook_time")
    int cookTime;
    String img;
    @Column(name = "cook_detail")
    String cookDetail;
    @Column(name = "create_at")
    Date createAt;

    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<RatingRecipe> ratingRecipes;

    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<IngredientRecipe> ingredientRecipes;

    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<OtherImageRecipe> otherImageRecipes;

    @OneToMany(mappedBy = "recipe",cascade = CascadeType.ALL)
    @JsonManagedReference
    Set<NoteRecipe> noteRecipes;

    @ManyToMany(mappedBy = "bookmarkRecipes")
    @JsonBackReference
    Set<User> bookmarkUsers;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_collection",
            joinColumns = @JoinColumn(name = "recipe_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "topic_id",referencedColumnName = "id")
    )
    @JsonManagedReference
    Set<Topic> recipeTopics;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "recipe_filter",
            joinColumns = @JoinColumn(name = "recipe_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "recipe_category_id",referencedColumnName = "id")
    )
    @JsonManagedReference
    Set<RecipeCategory> recipeCategories;
}
