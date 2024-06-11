package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.NoteRecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.NoteRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.request.recipe.OtherImageRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.NoteRecipeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.OtherImageRecipeResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.NoteRecipe;
import com.bodyupbe.bodyupbe.model.recipe.OtherImageRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.repository.NoteRecipeRepository;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NoteRecipeService {
    NoteRecipeRepository noteRecipeRepository;
    NoteRecipeMapper noteRecipeMapper;
    RecipeRepository recipeRepository;
    public NoteRecipeResponseDto addNoteRecipe(int recipeId, NoteRecipeRequestDto request) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new RuntimeException("Recipe not found"));
        NoteRecipe noteRecipe = NoteRecipe.builder()
                .detail(request.getDetail())
                .recipe(recipe)
                .build();
        return noteRecipeMapper.toResponseDto(noteRecipeRepository.save(noteRecipe));
    }
    public NoteRecipeResponseDto getNoteRecipeById(int noteRecipeId) {
        return noteRecipeMapper.toResponseDto(noteRecipeRepository.findById(noteRecipeId).orElseThrow(()->new RuntimeException("Other Image not found")));
    }
    public List<NoteRecipeResponseDto> findOtherImageRecipeByRecipeId(int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new RuntimeException("Recipe not found"));
        return noteRecipeMapper.toResponseDtoList(noteRecipeRepository.findNoteRecipeByRecipe(recipe));
    }
    public List<NoteRecipeResponseDto> getAllNoteRecipe() {
        return noteRecipeMapper.toResponseDtoList(noteRecipeRepository.findAll());
    }

    public NoteRecipeResponseDto updateNoteRecipe(int noteRecipeId, NoteRecipeRequestDto request) {
        NoteRecipe noteRecipe = noteRecipeRepository.findById(noteRecipeId).orElseThrow(()->new RuntimeException("Note Recipe Not found"));
        noteRecipe.setDetail(request.getDetail());
        return noteRecipeMapper.toResponseDto(noteRecipeRepository.save(noteRecipe));
    }

    public String deleteOtherImageRecipe(int otherImageRecipeId) {
        noteRecipeRepository.deleteById(otherImageRecipeId);
        return "Note recipe Deleted";
    }
}
