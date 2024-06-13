package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeCategoryRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategoryResponseSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategorySlimAndSetRecipeSlimResponseDto;
import com.bodyupbe.bodyupbe.service.recipe.RecipeCategoryService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe/recipe-category")
@RequiredArgsConstructor
@CrossOrigin
public class RecipeCategoryController {
    RecipeCategoryService recipeCategoryService;
    @PostMapping("/add")
    public ResponseEntity<RecipeCategorySlimAndSetRecipeSlimResponseDto> addRecipe(@RequestBody RecipeCategoryRequestDto request){
        return ResponseEntity.ok(recipeCategoryService.addRecipeCategory(request));
    }
    @GetMapping("/id")
    public ResponseEntity<RecipeCategorySlimAndSetRecipeSlimResponseDto> getRecipeById(@RequestParam int recipeCategoryId){
        return ResponseEntity.ok(recipeCategoryService.getRecipeCategoryById(recipeCategoryId));
    }
    @GetMapping("/all")
    public ResponseEntity<Set<RecipeCategorySlimAndSetRecipeSlimResponseDto>> getAllRecipe(){
        return ResponseEntity.ok(recipeCategoryService.getAllRecipeCategories());
    }
    @PutMapping("/update")
    public ResponseEntity<RecipeCategorySlimAndSetRecipeSlimResponseDto> updateRecipe(@RequestParam int recipeCategoryId, @RequestBody  RecipeCategoryRequestDto request){
        return ResponseEntity.ok(recipeCategoryService.updateRecipeCategory(recipeCategoryId,request));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRecipe(@RequestParam int recipeCategoryId) {
        return ResponseEntity.ok(recipeCategoryService.deleteRecipeCategory(recipeCategoryId));
    }
    @GetMapping("/popular")
        public ResponseEntity<Set<RecipeCategoryResponseSlimDto>> getPopularCategory(){
        return ResponseEntity.ok(recipeCategoryService.getPopularCategory());
    }
}

