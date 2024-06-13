package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RatingRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RatingRecipeSlimAndRecipeSlimUserSlimResponseDto;
import com.bodyupbe.bodyupbe.service.recipe.RatingRecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe/recipe-rating")
@RequiredArgsConstructor
@CrossOrigin
public class RatingRecipeController {
    RatingRecipeService ratingRecipeService;
    @PostMapping("/rating")
    public ResponseEntity<RatingRecipeSlimAndRecipeSlimUserSlimResponseDto> toggleRating(@RequestParam int recipeId, @RequestParam int userId, @RequestBody RatingRecipeRequestDto request) {
        return ResponseEntity.ok(ratingRecipeService.rating(recipeId, userId, request));
    }
    @GetMapping
    public ResponseEntity<Integer> getRating(@RequestParam int recipeId,@RequestParam int userId) {
        return ResponseEntity.ok(ratingRecipeService.getRating(recipeId, userId));
    }
}
