package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.NoteRecipeDto;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeDto;
import com.bodyupbe.bodyupbe.model.recipe.NoteRecipe;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NoteRecipeMapper {
    NoteRecipe toNoteRecipe(NoteRecipeDto noteRecipeDto);
    NoteRecipeDto toNoteRecipeDto(NoteRecipe noteRecipe);
}
