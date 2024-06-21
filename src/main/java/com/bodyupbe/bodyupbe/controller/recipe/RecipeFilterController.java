package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeResponseDto;
import com.bodyupbe.bodyupbe.service.recipe.RecipeFilterService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe/recipe-filter")
@RequiredArgsConstructor
@CrossOrigin
public class RecipeFilterController {
    RecipeFilterService recipeFilterService;

//    @GetMapping("/list")
//    public ResponseEntity<Set<RecipeResponseDto>> getAllBySetCategory(@RequestBody Set<Integer> recipeCategoryIds){
//        return ResponseEntity.ok(recipeFilterService.(recipeCategoryIds));
//    }

//    @PostMapping("/assign")
//    public ResponseEntity<RecipeResponseDto> assignCategoriesToRecipe(@RequestParam int recipeId, @RequestBody Set<Integer> categoryIds){
//        return ResponseEntity.ok(recipeFilterService.assignCategoriesToRecipe(recipeId, categoryIds));
//    }
//
//    @PutMapping("/update")
//    public ResponseEntity<RecipeResponseDto> updateCategoriesToRecipe(@RequestParam int recipeId, @RequestBody Set<Integer> categoryIds){
//        return ResponseEntity.ok(recipeFilterService.updateCategoriesToRecipe(recipeId, categoryIds));
//    }
//    @GetMapping("/id")
//    public ResponseEntity<Set<RecipeResponseDto>> getAllRecipesByCategoryId(@RequestParam int categoryId){
//        return ResponseEntity.ok(recipeFilterService.getAllRecipesByCategoryId(categoryId));
//    }



}
