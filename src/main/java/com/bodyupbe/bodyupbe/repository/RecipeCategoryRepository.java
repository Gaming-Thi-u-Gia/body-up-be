package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategoryResponseSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategoryTableResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.RecipeCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory,Integer> {
    Set<RecipeCategory> findAllByIdIn(Set<Integer> ids);
    @Query("SELECT rc FROM RecipeCategory rc JOIN rc.recipes r GROUP BY rc ORDER BY COUNT(r) DESC")
    List<RecipeCategory> findTop4CategoriesWithMostRecipes(Pageable pageable);
    @Query("SELECT DISTINCT rc.type  FROM RecipeCategory rc")
    Set<String> getTypeRecipeCategory();
    @Query("SELECT rc FROM RecipeCategory rc WHERE rc.type = :type")
    Set<RecipeCategory> getRecipeCategoriesByType(String type);
}
