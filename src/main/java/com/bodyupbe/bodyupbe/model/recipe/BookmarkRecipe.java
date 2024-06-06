package com.bodyupbe.bodyupbe.model.recipe;

import com.bodyupbe.bodyupbe.model.user.User;
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
@Table(name = "bookmark_recipes")
public class BookmarkRecipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @ManyToOne
    @JoinColumn(name="user_id",referencedColumnName = "id")
    @JsonBackReference
    User user;
    @ManyToOne
    @JoinColumn(name="recipe_id",referencedColumnName = "id")
    @JsonBackReference
    Recipe recipe;
}