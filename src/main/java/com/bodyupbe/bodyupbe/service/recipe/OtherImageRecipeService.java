package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.OtherImageRecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.OtherImageRecipeDto;
import com.bodyupbe.bodyupbe.model.recipe.OtherImageRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.repository.OtherImageRecipeRepository;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;

import java.util.List;
import java.util.stream.Collectors;

public class OtherImageRecipeService {
    OtherImageRecipeRepository otherImageRecipeRepository;
    RecipeRepository recipeRepository;
    OtherImageRecipeMapper otherImageRecipeMapper;
    public OtherImageRecipeDto addOtherImageRecipe(int recipeId, OtherImageRecipeDto request) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        OtherImageRecipe otherImageRecipe = otherImageRecipeMapper.toOtherImageRecipe(request);
        otherImageRecipe.setRecipe(recipe);
        return otherImageRecipeMapper.toOtherImageRecipeDto(otherImageRecipeRepository.save(otherImageRecipe));
    }

    public OtherImageRecipeDto getOtherImageRecipeById(int id) {
        OtherImageRecipe otherImageRecipe = otherImageRecipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OtherImageRecipe not found"));
        return otherImageRecipeMapper.toOtherImageRecipeDto(otherImageRecipe);
    }

    public List<OtherImageRecipeDto> getAllOtherImageRecipes() {
        return otherImageRecipeRepository.findAll().stream().map(otherImageRecipe ->  otherImageRecipeMapper.toOtherImageRecipeDto(otherImageRecipe)).collect(Collectors.toList());
    }

    public OtherImageRecipeDto updateOtherImageRecipe(int id, OtherImageRecipeDto request) {
        OtherImageRecipe otherImageRecipe = otherImageRecipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OtherImageRecipe not found"));
        otherImageRecipe.setImg(request.getImg());
        return otherImageRecipeMapper.toOtherImageRecipeDto(otherImageRecipeRepository.save(otherImageRecipe));
    }

    public void deleteOtherImageRecipe(int id) {
        OtherImageRecipe otherImageRecipe = otherImageRecipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OtherImageRecipe not found"));
        otherImageRecipeRepository.delete(otherImageRecipe);
    }
}
