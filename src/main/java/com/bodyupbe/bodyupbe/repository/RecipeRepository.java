package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe,Integer> {

}
