package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.RatingRecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.RatingRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RatingRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.RatingRecipeRepository;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RatingRecipeService {
    RecipeRepository recipeRepository;
    UserRepository userRepository;
    RatingRecipeRepository ratingRecipeRepository;
    RatingRecipeMapper ratingRecipeMapper;
    public RatingRecipeResponseSlimDto rating(int recipeId, int userId, RatingRecipeRequestDto request) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found"));
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        RatingRecipe ratingRecipe = ratingRecipeRepository.findRatingByRecipeAndUser(recipe, user);
        if (ratingRecipe != null) {
            ratingRecipe.setStar(request.getStar());
            ratingRecipeRepository.save(ratingRecipe);
        } else {
            ratingRecipe = new RatingRecipe();
            ratingRecipe.setRecipe(recipe);
            ratingRecipe.setUser(user);
            ratingRecipe.setStar(request.getStar());
             ratingRecipeRepository.save(ratingRecipe);
        }
        double avgStar = updateAverageRating(recipe);
        RatingRecipeResponseSlimDto ratingRecipeResponseSlimDto = ratingRecipeMapper.toRatingRecipeResponseSlimDto(ratingRecipe);
        ratingRecipeResponseSlimDto.setAvgStar(avgStar);
        ratingRecipeResponseSlimDto.setTotalRating(recipe.getRatingRecipes().size());
        return ratingRecipeResponseSlimDto;
    }
    
    public double updateAverageRating(Recipe recipe) {
        int totalStar = 0;
        Set<RatingRecipe> ratingRecipes = recipe.getRatingRecipes();
        for (RatingRecipe ratingRecipe : ratingRecipes) {
            totalStar += ratingRecipe.getStar();
        }
        double averageRatingRecipe = 0;
        if (!ratingRecipes.isEmpty()) {
            averageRatingRecipe = (double) totalStar / ratingRecipes.size();
        }
        recipe.setAvgStar(averageRatingRecipe);
        return recipeRepository.save(recipe).getAvgStar();
    }
//    public int getRating(int recipeId, Optional<Integer> userId) {
//        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found"));
//        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
//        return ratingRecipeRepository.findRatingByRecipeAndUser(recipe, user).getStar();
//    }
}
