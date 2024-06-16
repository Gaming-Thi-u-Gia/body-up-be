package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCardResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeDetailResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeLatestResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.recipe.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<RecipeResponseDto> addRecipe(@RequestBody RecipeRequestDto request) {
        return ResponseEntity.ok(recipeService.addRecipe(request));
    }

    @GetMapping("/id")
    public ResponseEntity<RecipeDetailResponseDto> getRecipeById(@RequestParam int recipeId, @RequestParam Optional<Integer> userId) {
        return ResponseEntity.ok(recipeService.getRecipeById(recipeId, userId));
    }



    @GetMapping("/all")
    public ResponseEntity<Set<RecipeResponseDto>> getAllRecipe() {
        return ResponseEntity.ok(recipeService.getAllRecipe());
    }

    @PutMapping("/update")
    public ResponseEntity<RecipeResponseDto> updateRecipe(@RequestParam int recipeId, @RequestBody RecipeRequestDto request) {
        return ResponseEntity.ok(recipeService.updateRecipe(recipeId, request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRecipe(@RequestParam int recipeId) {
        return ResponseEntity.ok(recipeService.deleteRecipe(recipeId));
    }

    @GetMapping("/latest")
    public ResponseEntity<Set<RecipeLatestResponseDto>> getLatestRecipe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        if (user.isPresent()) {
            return ResponseEntity.ok(recipeService.getLatestRecipe(Optional.of(user.get().getId())));
        } else {
            return ResponseEntity.ok(recipeService.getLatestRecipe(Optional.empty()));
        }
    }
    @GetMapping("/saved-recipe")
    public ResponseEntity<Set<RecipeCardResponseDto>> getSavedBookmark() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        if (user.isPresent()) {
            return ResponseEntity.ok(recipeService.getAllBookmarkedRecipe(user.get().getId()));
        } else {
            throw  new RuntimeException("User not found");
        }
    }
    @GetMapping("/name")
    public ResponseEntity<Set<RecipeCardResponseDto>> getRecipeByName(@RequestParam String recipeName) {
        return ResponseEntity.ok(recipeService.getRecipeByName(recipeName));
    }
    @GetMapping("/category")
    public ResponseEntity<Set<RecipeCardResponseDto>> getRecipeByCategory(@RequestParam Set<Integer> categoryIds) {
        return ResponseEntity.ok(recipeService.getRecipeByCategory(categoryIds, categoryIds.size()));
    }
}
