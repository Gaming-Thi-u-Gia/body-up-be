package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.NoteRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.NoteRecipeAndSetRecipeResponseDto;
import com.bodyupbe.bodyupbe.service.recipe.NoteRecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe/note-recipe")
@RequiredArgsConstructor
@CrossOrigin
public class NoteRecipeController {
    NoteRecipeService noteRecipeService;

    @PostMapping("/add")
    public ResponseEntity<NoteRecipeAndSetRecipeResponseDto> addNoteRecipeService(@RequestParam int recipeId, @RequestBody NoteRecipeRequestDto request) {
        return ResponseEntity.ok(noteRecipeService.addNoteRecipe(recipeId, request));
    }

    @GetMapping("/id")
    public ResponseEntity<NoteRecipeAndSetRecipeResponseDto> getNoteRecipeById(@RequestParam int noteRecipeId) {
        return ResponseEntity.ok(noteRecipeService.getNoteRecipeById(noteRecipeId));
    }

    @GetMapping("/all")
    public ResponseEntity<Set<NoteRecipeAndSetRecipeResponseDto>> getAllNoteRecipe() {
        return ResponseEntity.ok(noteRecipeService.getAllNoteRecipe());
    }

    @GetMapping("/recipe")
    public ResponseEntity<Set<NoteRecipeAndSetRecipeResponseDto>> getAllNoteRecipeByRecipeId(@RequestParam int recipeId) {
        return ResponseEntity.ok(noteRecipeService.findOtherImageRecipeByRecipeId(recipeId));
    }

    @PutMapping("/update")
    public ResponseEntity<NoteRecipeAndSetRecipeResponseDto> updateNoteRecipe(@RequestParam int noteRecipeId, @RequestBody NoteRecipeRequestDto request) {
        return ResponseEntity.ok(noteRecipeService.updateNoteRecipe(noteRecipeId, request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOtherImageRecipe(@RequestParam int noteRecipeId) {
        return ResponseEntity.ok(noteRecipeService.deleteOtherImageRecipe(noteRecipeId));
    }
}
