package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe,Integer> {
    List<Recipe> findRecipeByName(String name);
    @Query("SELECT r FROM Recipe r JOIN r.recipeCategories rc WHERE rc.id IN :categoryIds")
    Set<Recipe> findByRecipeCategories_IdIn(@Param("categoryIds") Set<Integer> categoryIds);

}
