package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.IngredientRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.IngredientRecipeResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.IngredientRecipe;
import com.bodyupbe.bodyupbe.service.recipe.IngredientRecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe/ingredient-recipe")
@RequiredArgsConstructor
@CrossOrigin
public class IngredientRecipeController {
    IngredientRecipeService ingredientRecipeService;
    @PostMapping("/add")
    public ResponseEntity<IngredientRecipeResponseDto> addIngredientRecipeService(@RequestParam int recipeId, @RequestBody IngredientRecipeRequestDto request) {
        return ResponseEntity.ok(ingredientRecipeService.addIngredientRecipe(recipeId,request));
    }
    @GetMapping("/id")
    public ResponseEntity<IngredientRecipeResponseDto> getIngredientRecipeById(@RequestParam int ingredientRecipeId) {
        return ResponseEntity.ok(ingredientRecipeService.getIngredientRecipeById(ingredientRecipeId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<IngredientRecipeResponseDto>> getAllIngredientRecipe(){
        return ResponseEntity.ok(ingredientRecipeService.getAllIngredientRecipe());
    }
    @GetMapping("/recipe")
    public ResponseEntity<List<IngredientRecipeResponseDto>> getAllIngredientRecipeByRecipeId(@RequestParam int recipeId) {
        return ResponseEntity.ok(ingredientRecipeService.findIngredientRecipeByRecipeId(recipeId));
    }
    @PutMapping("/update")
    public ResponseEntity<IngredientRecipeResponseDto> updateIngredientRecipe(@RequestParam int ingredientRecipeId , @RequestBody IngredientRecipeRequestDto request) {
        return ResponseEntity.ok(ingredientRecipeService.updateIngredientRecipe(ingredientRecipeId,request));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteIngredientRecipe(@RequestParam int ingredientRecipeId) {
        return ResponseEntity.ok(ingredientRecipeService.deleteIngredientRecipe(ingredientRecipeId));
    }
}
