package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCardResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeDetailResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeFilterResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeLatestResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectResponse;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectSetResponse;
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

//    @PostMapping("/add")
//    public ResponseEntity<RecipeResponseDto> addRecipe(@RequestBody RecipeRequestDto request) {
//        return ResponseEntity.ok(recipeService.addRecipe(request));
//    }
    //@GetMapping("/all")
    //public ResponseEntity<Set<RecipeResponseDto>> getAllRecipe() {
    //    return ResponseEntity.ok(recipeService.getAllRecipe());
    //}
//    @PutMapping("/update")
//    public ResponseEntity<RecipeResponseDto> updateRecipe(@RequestParam int recipeId, @RequestBody RecipeRequestDto request) {
//        return ResponseEntity.ok(recipeService.updateRecipe(recipeId, request));
//    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<String> deleteRecipe(@RequestParam int recipeId) {
//        return ResponseEntity.ok(recipeService.deleteRecipe(recipeId));
//    }

    @GetMapping("/latest")
    public ResponseEntity<Set<RecipeLatestResponseDto>> getLatestRecipe() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.ok(recipeService.getLatestRecipe(user.isPresent() ? Optional.of(user.get().getId()) : Optional.empty()));
    }

    @GetMapping("/id")
    public ResponseEntity<RecipeDetailResponseDto> getRecipeById(@RequestParam int recipeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.ok(recipeService.getRecipeById(recipeId, user.isPresent() ? Optional.of(user.get().getId()) : Optional.empty()));
    }

    @GetMapping("/saved-recipe")
    public ResponseEntity<ObjectSetResponse<RecipeCardResponseDto>> getSavedBookmark(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "4") int pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        if (user.isPresent()) {
            return ResponseEntity.ok(recipeService.getAllBookmarkedRecipe(user.get().getId(), pageNo, pageSize));
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @GetMapping("/name")
    public ResponseEntity<ObjectSetResponse<RecipeCardResponseDto>> getRecipeByName(@RequestParam String recipeName, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "4") int pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.ok(recipeService.getRecipeByName(recipeName, user.isPresent() ? Optional.of(user.get().getId()) : Optional.empty(), pageNo, pageSize));
    }

    @GetMapping("/category")
    public ResponseEntity<ObjectResponse<RecipeFilterResponseDto>> getRecipeByCategory(@RequestParam Set<Integer> categoryIds, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "4") int pageSize) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.ok(recipeService.getRecipeByCategory(categoryIds, user.isPresent() ? Optional.of(user.get().getId()) : Optional.empty(), pageNo, pageSize));
    }
}
