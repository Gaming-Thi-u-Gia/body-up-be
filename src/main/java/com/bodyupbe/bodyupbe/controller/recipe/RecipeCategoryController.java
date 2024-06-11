package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeCategoryRequestDto;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategoryResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeResponseDto;
import com.bodyupbe.bodyupbe.service.recipe.RecipeCategoryService;
import com.bodyupbe.bodyupbe.service.recipe.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe/recipe-category")
@RequiredArgsConstructor
@CrossOrigin
public class RecipeCategoryController {
    RecipeCategoryService recipeCategoryService;
    @PostMapping("/add")
    public ResponseEntity<RecipeCategoryResponseDto> addRecipe(@RequestBody RecipeCategoryRequestDto request){
        return ResponseEntity.ok(recipeCategoryService.addRecipeCategory(request));
    }
    @GetMapping("/id")
    public ResponseEntity<RecipeCategoryResponseDto> getRecipeById(@RequestParam int recipeCategoryId){
        return ResponseEntity.ok(recipeCategoryService.getRecipeCategoryById(recipeCategoryId));
    }
    @GetMapping("/all")
    public ResponseEntity<List<RecipeCategoryResponseDto>> getAllRecipe(){
        return ResponseEntity.ok(recipeCategoryService.getAllRecipeCategories());
    }
    @PutMapping("/update")
    public ResponseEntity<RecipeCategoryResponseDto> updateRecipe(@RequestParam int recipeCategoryId,@RequestBody  RecipeCategoryRequestDto request){
        return ResponseEntity.ok(recipeCategoryService.updateRecipeCategory(recipeCategoryId,request));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRecipe(@RequestParam int recipeCategoryId) {
        return ResponseEntity.ok(recipeCategoryService.deleteRecipeCategory(recipeCategoryId));
    }
}

