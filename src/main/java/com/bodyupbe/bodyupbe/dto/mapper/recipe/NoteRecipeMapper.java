package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.NoteRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.NoteRecipeAndSetRecipeResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.NoteRecipe;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface NoteRecipeMapper {
     NoteRecipeAndSetRecipeResponseDto toNoteRecipeAndSetRecipeResponseDto(NoteRecipe noteRecipe);
     NoteRecipe toNoteRecipe(NoteRecipeRequestDto request);
     Set<NoteRecipeAndSetRecipeResponseDto> toSetNoteRecipeAndSetRecipeResponseDto(List<NoteRecipe> noteRecipes);
}
