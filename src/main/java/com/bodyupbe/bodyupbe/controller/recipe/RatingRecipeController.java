package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RatingRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RatingRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.recipe.RatingRecipeService;
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
@RequestMapping("/api/v1/recipe/recipe-rating")
@RequiredArgsConstructor
@CrossOrigin
public class RatingRecipeController {
    RatingRecipeService ratingRecipeService;
    private final UserRepository userRepository;

    @PostMapping("/rating")
    public ResponseEntity<RatingRecipeResponseSlimDto> toggleRating(@RequestParam int recipeId, @RequestBody RatingRecipeRequestDto request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found");
        }
        return ResponseEntity.ok(ratingRecipeService.rating(recipeId, user.get().getId(), request));
    }
//    @GetMapping
//    public ResponseEntity<Integer> getRating(@RequestParam int recipeId) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        String currentPrincipalName = authentication.getName();
//        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
//        if(user.isEmpty()){
//            throw new RuntimeException("User not found");
//        }
//        return ResponseEntity.ok(ratingRecipeService.getRating(recipeId, user.get().getId()));
//    }
}
