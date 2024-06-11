package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.IngredientRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRecipeRepository extends JpaRepository<IngredientRecipe,Integer> {
    List<IngredientRecipe> findIngredientRecipeByRecipe(Recipe recipe);
}
