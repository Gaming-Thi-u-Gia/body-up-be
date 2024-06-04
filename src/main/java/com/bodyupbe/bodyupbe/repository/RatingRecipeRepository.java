package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRecipeRepository extends JpaRepository<RatingRecipe,Integer> {
}
