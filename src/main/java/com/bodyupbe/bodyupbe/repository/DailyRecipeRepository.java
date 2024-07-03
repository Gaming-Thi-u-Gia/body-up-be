package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.DailyRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DailyRecipeRepository extends JpaRepository<DailyRecipe,Integer>{
}
