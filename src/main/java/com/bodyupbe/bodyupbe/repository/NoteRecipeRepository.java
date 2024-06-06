package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.NoteRecipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRecipeRepository  extends JpaRepository<NoteRecipe, Integer> {
}
