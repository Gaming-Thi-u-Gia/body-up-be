package com.bodyupbe.bodyupbe.repository;

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

}
