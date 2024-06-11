package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.NoteRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.request.recipe.OtherImageRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.NoteRecipeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.OtherImageRecipeResponseDto;
import com.bodyupbe.bodyupbe.service.recipe.NoteRecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe/note-recipe")
@RequiredArgsConstructor
@CrossOrigin
public class NoteRecipeController {
    NoteRecipeService noteRecipeService;
    @PostMapping("/add")
    public ResponseEntity<NoteRecipeResponseDto> addNoteRecipeService(@RequestParam int recipeId, @RequestBody NoteRecipeRequestDto request) {
        return ResponseEntity.ok(noteRecipeService.addNoteRecipe(recipeId,request));
    }
    @GetMapping("/id")
    public ResponseEntity<NoteRecipeResponseDto> getNoteRecipeById(@RequestParam int noteRecipeId) {
        return ResponseEntity.ok(noteRecipeService.getNoteRecipeById(noteRecipeId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<NoteRecipeResponseDto>> getAllNoteRecipe(){
        return ResponseEntity.ok(noteRecipeService.getAllNoteRecipe());
    }
    @GetMapping("/recipe")
    public ResponseEntity<List<NoteRecipeResponseDto>> getAllNoteRecipeByRecipeId(@RequestParam int recipeId) {
        return ResponseEntity.ok(noteRecipeService.findOtherImageRecipeByRecipeId(recipeId));
    }
    @PutMapping("/update")
    public ResponseEntity<NoteRecipeResponseDto> updateNoteRecipe(@RequestParam int noteRecipeId , @RequestBody NoteRecipeRequestDto request) {
        return ResponseEntity.ok(noteRecipeService.updateNoteRecipe(noteRecipeId,request));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOtherImageRecipe(@RequestParam int noteRecipeId) {
        return ResponseEntity.ok(noteRecipeService.deleteOtherImageRecipe(noteRecipeId));
    }
}
