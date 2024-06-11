package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.response.user.UserBookmarkRecipeDto;
import com.bodyupbe.bodyupbe.service.recipe.BookmarkRecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe/bookmark-recipe")
@RequiredArgsConstructor
@CrossOrigin
public class BookmarkRecipeController {
    BookmarkRecipeService bookmarkRecipeService;
    @PostMapping("/toggle")
    public ResponseEntity<Optional<UserBookmarkRecipeDto>> addBookmarkRecipe(@RequestParam int userId, @RequestParam int recipeId) {
        return ResponseEntity.ok(bookmarkRecipeService.toggleBookmarkRecipe(userId,recipeId));
    }
    @GetMapping
    public ResponseEntity<UserBookmarkRecipeDto>  getBookmarkRecipe(@RequestParam int userId) {
        return ResponseEntity.ok(bookmarkRecipeService.getSetBookmarkRecipeByUserId(userId));
    }
}
