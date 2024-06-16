package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.*;
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

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeService {
    RecipeMapper recipeMapper;
    RecipeRepository recipeRepository;
    UserRepository userRepository;
    private final RatingRecipeRepository ratingRecipeRepository;

    public RecipeResponseDto addRecipe(RecipeRequestDto request) {
        Recipe recipe = recipeMapper.toRecipe(request);
        return recipeMapper.toRecipeResponseDto(recipeRepository.save(recipe));
    }

    public RecipeResponseDto getRecipe(int id) {
        return recipeMapper.toRecipeResponseDto(recipeRepository.findById(id).orElseThrow(() ->
                new RuntimeException("Recipe not found")));
    }

    public RecipeDetailResponseDto getRecipeById(int recipeId, Optional<Integer> userId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() ->
                new RuntimeException("Recipe not found"));
        RecipeDetailResponseDto recipeDetailResponseDto = recipeMapper.toRecipeDetailResponseDto(recipe);
        if (userId.isPresent()) {
            boolean isBookmarked = recipeRepository.findBookmarkedByUserIdAndRecipeId(userId.get(), recipeId);
            recipeDetailResponseDto.setBookmarked(isBookmarked);
            Optional<RatingRecipe> ratingRecipe = recipeRepository.findRatingStarRecipeByUserId(userId.get(), recipeId);
            if (ratingRecipe.isPresent()) {
                recipeDetailResponseDto.setCurrentRating(ratingRecipe.get().getStar());
            } else {
                recipeDetailResponseDto.setCurrentRating(0);
            }
        }
        int totalRating = recipeRepository.countRatingRecipesByRecipeId(recipeId);
        recipeDetailResponseDto.setTotalRating(totalRating);
        return recipeDetailResponseDto;
    }

    public Set<RecipeResponseDto> getAllRecipe() {
        return recipeMapper.toSetRecipeResponseDto(recipeRepository.findAll());
    }

    public RecipeResponseDto updateRecipe(int recipeId, RecipeRequestDto request) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() ->
                new RuntimeException("Recipe not found"));
        recipe.setName(request.getName());
        recipe.setAvgStar(request.getAvgStar());
        recipe.setPrepTime(request.getPrepTime());
        recipe.setCookTime(request.getCookTime());
        return recipeMapper.toRecipeResponseDto(recipeRepository.save(recipe));
    }

    public String deleteRecipe(int recipeId) {
        recipeRepository.deleteById(recipeId);
        return "Recipe with id" + recipeId + " deleted";
    }

    public Set<RecipeLatestResponseDto> getLatestRecipe(Optional<Integer> userId) {
        Set<Recipe> recipes = recipeRepository.findTop2ByOrderByCreateAtDesc();
        Set<RecipeLatestResponseDto> recipeLatestResponseDtos = recipeMapper.toSetRecipeLatestResponseDto(recipes);
        recipeLatestResponseDtos.forEach(recipeLatestResponseDto -> {
                    if (userId.isPresent()) {
                        boolean isBookmarked = recipeRepository.findBookmarkedByUserIdAndRecipeId(userId.get(), recipeLatestResponseDto.getId());
                        recipeLatestResponseDto.setBookmarked(isBookmarked);
                        Optional<RatingRecipe> ratingRecipe = recipeRepository.findRatingStarRecipeByUserId(userId.get(), recipeLatestResponseDto.getId());
                        if (ratingRecipe.isPresent()) {
                            recipeLatestResponseDto.setCurrentRating(ratingRecipe.get().getStar());
                        } else {
                            recipeLatestResponseDto.setCurrentRating(0);
                        }
                    }
                }
        );
        return recipeLatestResponseDtos;
    }

    public Set<RecipeCardResponseDto> getAllBookmarkedRecipe(int userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Set<RecipeCardResponseDto> setRecipeCardResponseDtos = recipeMapper.toSetRecipeCardResponseDto(user.getBookmarkRecipes());
        setRecipeCardResponseDtos.forEach(recipeCardResponseDto -> {
            Optional<RatingRecipe> ratingRecipe = ratingRecipeRepository.findRatingRecipeByRecipe_IdAndUser_Id(userId, recipeCardResponseDto.getId());
            if (ratingRecipe.isPresent()) {
                recipeCardResponseDto.setCurrentRating(ratingRecipe.get().getStar());
            } else {
                recipeCardResponseDto.setCurrentRating(0);
            }
            recipeCardResponseDto.setBookmarked(recipeRepository.findBookmarkedByUserIdAndRecipeId(userId, recipeCardResponseDto.getId()));

        });
        return setRecipeCardResponseDtos;
    }
    public Set<RecipeCardResponseDto> getRecipeByName(String nameRecipe) {
        return recipeMapper.toSetRecipeCardResponseDto(recipeRepository.findRecipeByNameContainingIgnoreCase(nameRecipe));
    }
    public Set<RecipeCardResponseDto> getRecipeByCategory(Set<Integer> categoryIds, long CategorySize){
        return recipeMapper.toSetRecipeCardResponseDto(recipeRepository.findRecipesByCategoryIds(categoryIds, categoryIds.size()));
    }
}
