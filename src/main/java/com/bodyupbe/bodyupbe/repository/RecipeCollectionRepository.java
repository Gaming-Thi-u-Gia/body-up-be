package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.RecipesCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeCollectionRepository extends JpaRepository<RecipesCollection,Integer> {
}
