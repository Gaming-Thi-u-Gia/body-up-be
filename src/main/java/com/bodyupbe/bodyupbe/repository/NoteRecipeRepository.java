package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.NoteRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRecipeRepository extends JpaRepository<NoteRecipe,Integer> {
    List<NoteRecipe> findNoteRecipeByRecipe(Recipe recipe);
}
