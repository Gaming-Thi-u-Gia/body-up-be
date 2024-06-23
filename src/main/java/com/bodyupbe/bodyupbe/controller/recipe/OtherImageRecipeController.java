package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.OtherImageRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.OtherImageRecipeAndRecipeSlimResponseDto;
import com.bodyupbe.bodyupbe.service.recipe.OtherImageRecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe/other-image-recipe")
@RequiredArgsConstructor
@CrossOrigin
public class OtherImageRecipeController {
    OtherImageRecipeService otherImageRecipeService;

    @PostMapping("/add")
    public ResponseEntity<OtherImageRecipeAndRecipeSlimResponseDto> addOtherImageRecipe(@RequestParam int recipeId, @RequestBody OtherImageRecipeRequestDto request) {
        return ResponseEntity.ok(otherImageRecipeService.addOtherImageRecipe(recipeId, request));
    }

    @GetMapping("/id")
    public ResponseEntity<OtherImageRecipeAndRecipeSlimResponseDto> getOtherImageRecipeById(@RequestParam int otherImageRecipeId) {
        return ResponseEntity.ok(otherImageRecipeService.getOtherImageRecipeById(otherImageRecipeId));
    }

    @GetMapping("/all")
    public ResponseEntity<Set<OtherImageRecipeAndRecipeSlimResponseDto>> getAllOtherImageRecipe() {
        return ResponseEntity.ok(otherImageRecipeService.getAllOtherImageRecipe());
    }

    @GetMapping("/recipe")
    public ResponseEntity<Set<OtherImageRecipeAndRecipeSlimResponseDto>> getOtherImageRecipeListByRecipeId(@RequestParam int recipeId) {
        return ResponseEntity.ok(otherImageRecipeService.getOtherImageRecipeListByRecipeId(recipeId));
    }

    @PutMapping("/update")
    public ResponseEntity<OtherImageRecipeAndRecipeSlimResponseDto> updateOtherImageRecipe(@RequestParam int otherImageRecipeId, @RequestBody OtherImageRecipeRequestDto request) {
        return ResponseEntity.ok(otherImageRecipeService.updateOtherImageRecipe(otherImageRecipeId, request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteOtherImageRecipe(@RequestParam int otherImageRecipeId) {
        return ResponseEntity.ok(otherImageRecipeService.deleteOtherImageRecipe(otherImageRecipeId));
    }
}
