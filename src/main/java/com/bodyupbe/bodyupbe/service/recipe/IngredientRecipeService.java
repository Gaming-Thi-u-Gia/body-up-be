package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.IngredientRecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.IngredientRecipeDto;
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
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IngredientRecipeService {
    IngredientRecipeRepository ingredientRecipeRepository;
    RecipeRepository recipeRepository;
    IngredientRecipeMapper ingredientRecipeMapper;
    public IngredientRecipeDto addIngredientRecipe(int id, IngredientRecipeDto request) {
        Recipe recipe = recipeRepository.findById(id).orElseThrow(()->new RuntimeException("Ingredient recipe not found"));
        IngredientRecipe ingredientRecipe = ingredientRecipeMapper.toIngredientRecipe(request);
        ingredientRecipe.setRecipe(recipe);
        return ingredientRecipeMapper.toIngredientRecipeDto(ingredientRecipeRepository.save(ingredientRecipe));
    }
    public List<IngredientRecipeDto> getAllIngredientRecipes() {
        return ingredientRecipeRepository.findAll().stream().map(ingredientRecipe->ingredientRecipeMapper.toIngredientRecipeDto(ingredientRecipe)).collect(Collectors.toList());
    }
    public IngredientRecipeDto getIngredientRecipe(int id) {
        return ingredientRecipeMapper.toIngredientRecipeDto(ingredientRecipeRepository.findById(id).orElseThrow(()->new RuntimeException("Ingredient recipe not found")));
    }
    public IngredientRecipeDto updateIngredientRecipe(int id, IngredientRecipeDto request) {
        IngredientRecipe ingredientRecipe = ingredientRecipeRepository.findById(id).orElseThrow(()->new RuntimeException("Ingredient recipe not found"));
        return ingredientRecipeMapper.toIngredientRecipeDto(ingredientRecipeRepository.save(ingredientRecipeMapper.toIngredientRecipe(request)));
    }
    public String deleteIngredientRecipe(int id) {
        IngredientRecipe ingredientRecipe = ingredientRecipeRepository.findById(id).orElseThrow(()->new RuntimeException("Ingredient recipe not found"));
        ingredientRecipeRepository.deleteById(id);
        return "Ingredient recipe deleted: " + ingredientRecipe.getName()+"with id:"+id;
    }


}
