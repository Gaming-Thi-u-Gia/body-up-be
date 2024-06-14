package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeDetailResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeSlimAndSetRecipeCategorySlimResponseDto;
import com.bodyupbe.bodyupbe.service.recipe.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe")
@RequiredArgsConstructor
@CrossOrigin
public class RecipeController {
    RecipeService recipeService;
    @PostMapping("/add")
    public ResponseEntity<RecipeResponseDto> addRecipe(@RequestBody RecipeRequestDto request){
        return ResponseEntity.ok(recipeService.addRecipe(request));
    }
    @GetMapping("/id")
    public ResponseEntity<RecipeDetailResponseDto> getRecipeById(@RequestParam int recipeId,@RequestParam Optional<Integer> userId){
        return ResponseEntity.ok(recipeService.getRecipeById(recipeId,userId));
    }
    @GetMapping("/name")
    public ResponseEntity<List<RecipeResponseDto>> getRecipeByName(@RequestParam String recipeName){
        return ResponseEntity.ok(recipeService.getRecipeByName(recipeName));
    }
    @GetMapping("/all")
    public ResponseEntity<Set<RecipeResponseDto>> getAllRecipe(){
        return ResponseEntity.ok(recipeService.getAllRecipe());
    }
    @PutMapping("/update")
    public ResponseEntity<RecipeResponseDto> updateRecipe(@RequestParam int recipeId,@RequestBody  RecipeRequestDto request){
        return ResponseEntity.ok(recipeService.updateRecipe(recipeId,request));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRecipe(@RequestParam int recipeId) {
        return ResponseEntity.ok(recipeService.deleteRecipe(recipeId));
    }
    @GetMapping("/latest")
    public ResponseEntity<Set<RecipeSlimAndSetRecipeCategorySlimResponseDto>> getLatestRecipe(@RequestParam Optional<Integer> userId){
        return ResponseEntity.ok(recipeService.getLatestRecipe(userId));
    }
}
