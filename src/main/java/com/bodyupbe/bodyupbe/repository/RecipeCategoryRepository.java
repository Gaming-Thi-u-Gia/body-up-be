package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.recipe.RecipeCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface RecipeCategoryRepository extends JpaRepository<RecipeCategory,Integer> {
    Set<RecipeCategory> findAllByIdIn(Set<Integer> ids);
}
