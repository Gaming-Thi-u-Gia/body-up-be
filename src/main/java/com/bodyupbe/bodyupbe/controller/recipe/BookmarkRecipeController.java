package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.response.recipe.BookmarkSlimResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserBookmarkRecipeResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.recipe.BookmarkRecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/recipe/bookmark-recipe")
@RequiredArgsConstructor
@CrossOrigin
public class BookmarkRecipeController {
    BookmarkRecipeService bookmarkRecipeService;
    private final UserRepository userRepository;

    @PostMapping("/toggle")
    public ResponseEntity<BookmarkSlimResponseDto> addBookmarkRecipe(@RequestParam int recipeId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        if(user.isEmpty()){
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(bookmarkRecipeService.toggleBookmarkRecipe(user.get().getId(),recipeId));
    }
}
