package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.BookmarkRecipeDto;
import com.bodyupbe.bodyupbe.dto.response.ApiReponse;
import com.bodyupbe.bodyupbe.service.recipe.BookmarkRecipeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe/bookmark-recipe")
public class BookmarkRecipeController {
    BookmarkRecipeService bookmarkRecipeService;
    @PostMapping
    public ApiReponse<BookmarkRecipeDto> addBookmarkRecipe(@RequestParam int userId,@RequestParam int recipeId) {
        return ApiReponse.<BookmarkRecipeDto>builder()
                .result(bookmarkRecipeService.addBookmarkRecipe(userId,recipeId))
                .build();
    }
}
