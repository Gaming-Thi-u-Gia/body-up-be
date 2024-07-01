package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.response.recipe.DailyRecipesResponseDto;
import com.bodyupbe.bodyupbe.service.recipe.DailyRecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.Set;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe/ingredient-recipe")
@RequiredArgsConstructor
@CrossOrigin
public class DailyRecipeController {
    DailyRecipeService dailyRecipeService;
    @GetMapping("/getRecipesByDay")
    public ResponseEntity<Set<DailyRecipesResponseDto>> getRecipesByDay(@RequestParam String day, @RequestParam Integer workoutProgramId) {
        Set<DailyRecipesResponseDto> recipes = dailyRecipeService.getRecipesByDay(day, workoutProgramId);
        return ResponseEntity.ok(recipes);
    }
}
