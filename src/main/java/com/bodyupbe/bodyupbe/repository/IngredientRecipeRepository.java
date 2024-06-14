package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.IngredientRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRecipeRepository extends JpaRepository<IngredientRecipe,Integer> {
}
