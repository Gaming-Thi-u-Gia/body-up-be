package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.IngredientRecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.IngredientRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.IngredientRecipeResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.IngredientRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.repository.IngredientRecipeRepository;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IngredientRecipeService {
    IngredientRecipeRepository ingredientRecipeRepository;
    IngredientRecipeMapper ingredientRecipeMapper;
    RecipeRepository recipeRepository;
    public IngredientRecipeResponseDto addIngredientRecipe(int recipeId, IngredientRecipeRequestDto request) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new RuntimeException("Recipe not found"));
        IngredientRecipe ingredientRecipe = IngredientRecipe.builder()
                .amount(request.getAmount())
                .name(request.getName())
                .recipe(recipe)
                .build();
        return ingredientRecipeMapper.toResponseDto(ingredientRecipeRepository.save(ingredientRecipe));
    }
    public IngredientRecipeResponseDto getIngredientRecipeById (int ingredientRecipeId){
        return ingredientRecipeMapper.toResponseDto(ingredientRecipeRepository.findById(ingredientRecipeId).orElseThrow(()->new RuntimeException("Ingredient Recipe not found")));
    }
    public List<IngredientRecipeResponseDto> findIngredientRecipeByRecipeId(int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new RuntimeException("Recipe not found"));
        return ingredientRecipeMapper.toResponseDtoList(ingredientRecipeRepository.findIngredientRecipeByRecipe(recipe));
    }
    public List<IngredientRecipeResponseDto> getAllIngredientRecipe() {
        return ingredientRecipeMapper.toResponseDtoList(ingredientRecipeRepository.findAll());
    }

    public IngredientRecipeResponseDto updateIngredientRecipe(int ingredientRecipeId, IngredientRecipeRequestDto request) {
        IngredientRecipe ingredientRecipe = ingredientRecipeRepository.findById(ingredientRecipeId).orElseThrow(()->new RuntimeException("Ingredient Recipe Not Found"));
        ingredientRecipe.setAmount(request.getAmount());
        ingredientRecipe.setName(request.getName());
        return ingredientRecipeMapper.toResponseDto(ingredientRecipeRepository.save(ingredientRecipe));
    }

    public String deleteIngredientRecipe(int ingredientRecipeId){
        ingredientRecipeRepository.deleteById(ingredientRecipeId);
        return "Ingredient Recipe Deleted";
    }
}
