package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRecipeRepository extends JpaRepository<RatingRecipe,Integer> {
    RatingRecipe findRatingByRecipeAndUser(Recipe recipe, User user);

    int findRatingByRecipe_IdAndUser_Id(int userId, int recipeId);

    int countRatingRecipeByRecipe_Id(int recipeId);
}
