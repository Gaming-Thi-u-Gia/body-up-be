package com.bodyupbe.bodyupbe.dto.mapper.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.NoteRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.NoteRecipeResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.NoteRecipe;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NoteRecipeMapper {
     NoteRecipeResponseDto toResponseDto(NoteRecipe noteRecipe);
     NoteRecipe toEntity(NoteRecipeRequestDto request);
     List<NoteRecipeResponseDto> toResponseDtoList(List<NoteRecipe> noteRecipes);
}
