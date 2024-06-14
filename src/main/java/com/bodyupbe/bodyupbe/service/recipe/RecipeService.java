package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeDetailResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeSlimAndSetRecipeCategorySlimResponseDto;
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

    public RecipeResponseDto addRecipe(RecipeRequestDto request){
        Recipe recipe  = recipeMapper.toRecipe(request);
        return recipeMapper.toRecipeResponseDto(recipeRepository.save(recipe));
    }
    public RecipeResponseDto getRecipe(int id){
        return recipeMapper.toRecipeResponseDto(recipeRepository.findById(id).orElseThrow(()->
                new RuntimeException("Recipe not found")));
    }
    public List<RecipeResponseDto> getRecipeByName(String name) {
        return recipeRepository.findRecipeByName(name).stream().map(recipeMapper::toRecipeResponseDto).toList();
    }
    public RecipeDetailResponseDto getRecipeById(int recipeId, Optional<Integer> userId){
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() ->
                new RuntimeException("Recipe not found"));
        RecipeDetailResponseDto recipeDetailResponseDto = recipeMapper.toRecipeDetailResponseDto(recipe);
        if(userId.isPresent()){
            boolean isBookmarked = recipeRepository.findBookmarkedByUserIdAndRecipeId(userId.get(), recipeId);
            recipeDetailResponseDto.setBookmarked(isBookmarked);
            RatingRecipe userRating = ratingRecipeRepository.findRatingRecipeByRecipe_IdAndUser_Id(recipeId, recipeId)
                    .orElse(new RatingRecipe());
            recipeDetailResponseDto.setCurrentRating(userRating.getStar());
        }
        int totalRating = recipeRepository.countRatingRecipesByRecipeId(recipeId);
        recipeDetailResponseDto.setTotalRating(totalRating);
        return recipeDetailResponseDto;
    }

    public Set<RecipeResponseDto> getAllRecipe(){
        return recipeMapper.toSetRecipeResponseDto(recipeRepository.findAll());
    }
    public RecipeResponseDto updateRecipe(int recipeId, RecipeRequestDto request){
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->
                new RuntimeException("Recipe not found"));
        recipe.setName(request.getName());
        recipe.setAvgStar(request.getAvgStar());
        recipe.setPrepTime(request.getPrepTime());
        recipe.setCookTime(request.getCookTime());
        return recipeMapper.toRecipeResponseDto(recipeRepository.save(recipe));
    }
    public String deleteRecipe(int recipeId){
         recipeRepository.deleteById(recipeId);
        return "Recipe with id"+ recipeId +" deleted";
    }
    public Set<RecipeSlimAndSetRecipeCategorySlimResponseDto> getLatestRecipe(Optional<Integer> userId){
        Set<Recipe> recipe = recipeRepository.findTop2ByOrderByCreateAtDesc();
        Set<RecipeSlimAndSetRecipeCategorySlimResponseDto> recipeSlimAndSetRecipeCategorySlimResponseDtos = recipeMapper.toSetRecipeSlimAndSetRecipeCategorySlimResponseDto(recipe);
        if(userId.isPresent()){
            recipeSlimAndSetRecipeCategorySlimResponseDtos.forEach(recipeSlimAndSetRecipeCategorySlimResponseDto -> {
                boolean isBookmarked = recipeRepository.findBookmarkedByUserIdAndRecipeId(userId.get(), recipeSlimAndSetRecipeCategorySlimResponseDto.getId());
                recipeSlimAndSetRecipeCategorySlimResponseDto.setBookmarked(isBookmarked);
            }
            );
        };
        return recipeSlimAndSetRecipeCategorySlimResponseDtos;
    }
}
