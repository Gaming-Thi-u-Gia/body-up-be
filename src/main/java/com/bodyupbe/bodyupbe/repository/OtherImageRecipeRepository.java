package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.OtherImageRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtherImageRecipeRepository extends JpaRepository<OtherImageRecipe,Integer> {
    List<OtherImageRecipe> findOtherImageRecipeByRecipe(Recipe recipe);
}
