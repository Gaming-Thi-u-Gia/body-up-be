package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.NoteRecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.NoteRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.NoteRecipeAndSetRecipeResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.NoteRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.repository.NoteRecipeRepository;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoteRecipeService {
    NoteRecipeRepository noteRecipeRepository;
    NoteRecipeMapper noteRecipeMapper;
    RecipeRepository recipeRepository;
    public NoteRecipeAndSetRecipeResponseDto addNoteRecipe(int recipeId, NoteRecipeRequestDto request) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new RuntimeException("Recipe not found"));
        NoteRecipe noteRecipe = NoteRecipe.builder()
                .detail(request.getDetail())
                .recipe(recipe)
                .build();
        return noteRecipeMapper.toNoteRecipeAndSetRecipeResponseDto(noteRecipeRepository.save(noteRecipe));
    }
    public NoteRecipeAndSetRecipeResponseDto getNoteRecipeById(int noteRecipeId) {
        return noteRecipeMapper.toNoteRecipeAndSetRecipeResponseDto(noteRecipeRepository.findById(noteRecipeId).orElseThrow(()->new RuntimeException("Other Image not found")));
    }
    public Set<NoteRecipeAndSetRecipeResponseDto> findOtherImageRecipeByRecipeId(int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new RuntimeException("Recipe not found"));
        return noteRecipeMapper.toSetNoteRecipeAndSetRecipeResponseDto(noteRecipeRepository.findNoteRecipeByRecipe(recipe));
    }
    public Set<NoteRecipeAndSetRecipeResponseDto> getAllNoteRecipe() {
        return noteRecipeMapper.toSetNoteRecipeAndSetRecipeResponseDto(noteRecipeRepository.findAll());
    }

    public NoteRecipeAndSetRecipeResponseDto updateNoteRecipe(int noteRecipeId, NoteRecipeRequestDto request) {
        NoteRecipe noteRecipe = noteRecipeRepository.findById(noteRecipeId).orElseThrow(()->new RuntimeException("Note Recipe Not found"));
        noteRecipe.setDetail(request.getDetail());
        return noteRecipeMapper.toNoteRecipeAndSetRecipeResponseDto(noteRecipeRepository.save(noteRecipe));
    }

    public String deleteOtherImageRecipe(int otherImageRecipeId) {
        noteRecipeRepository.deleteById(otherImageRecipeId);
        return "Note recipe Deleted";
    }
}
