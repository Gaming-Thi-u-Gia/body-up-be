package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.BookmarkRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRecipeRepository extends JpaRepository<BookmarkRecipe,Integer> {
}
