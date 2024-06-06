//package com.bodyupbe.bodyupbe.service.recipe;
//
//import com.bodyupbe.bodyupbe.dto.mapper.recipe.NoteRecipeMapper;
//import com.bodyupbe.bodyupbe.dto.request.recipe.NoteRecipeDto;
//import com.bodyupbe.bodyupbe.model.recipe.NoteRecipe;
//import com.bodyupbe.bodyupbe.model.recipe.Recipe;
//import com.bodyupbe.bodyupbe.repository.NoteRecipeRepository;
//import com.bodyupbe.bodyupbe.repository.RecipeRepository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class NoteRecipeService {
//    NoteRecipeRepository noteRecipeRepository;
//    RecipeRepository recipeRepository;
//    NoteRecipeMapper noteRecipeMapper;
//    public NoteRecipeDto addNoteRecipe(int recipeId, NoteRecipeDto request) {
//        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found"));
//        NoteRecipe noteRecipe = noteRecipeMapper.toNoteRecipe(request);
//        noteRecipe.setRecipe(recipe);
//        return noteRecipeMapper.toNoteRecipeDto(noteRecipeRepository.save(noteRecipe));
//    }
//    public NoteRecipeDto getNoteRecipeById(int id) {
//        NoteRecipe noteRecipe = noteRecipeRepository.findById(id).orElseThrow(() -> new RuntimeException("NoteRecipe not found"));
//        return noteRecipeMapper.toNoteRecipeDto(noteRecipe);
//    }
//
//    public List<NoteRecipeDto> getAllNoteRecipes() {
//        return noteRecipeRepository.findAll().stream().map(noteRecipe -> noteRecipeMapper.toNoteRecipeDto(noteRecipe)).collect(Collectors.toList());
//    }
//
//    public NoteRecipeDto updateNoteRecipe(int id, NoteRecipeDto request) {
//        NoteRecipe noteRecipe = noteRecipeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("NoteRecipe not found"));
//        noteRecipe.setDetail(request.getDetail());
//        noteRecipe = noteRecipeRepository.save(noteRecipe);
//        return noteRecipeMapper.toNoteRecipeDto(noteRecipe);
//    }
//
//    public void deleteNoteRecipe(int id) {
//        NoteRecipe noteRecipe = noteRecipeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("NoteRecipe not found"));
//        noteRecipeRepository.delete(noteRecipe);
//    }
//}
