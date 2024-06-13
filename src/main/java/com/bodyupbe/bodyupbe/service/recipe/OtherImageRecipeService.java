package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.OtherImageRecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.OtherImageRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.OtherImageRecipeAndRecipeSlimResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.OtherImageRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.repository.OtherImageRecipeRepository;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtherImageRecipeService {
    OtherImageRecipeRepository otherImageRecipeRepository;
    OtherImageRecipeMapper otherImageRecipeMapper;
    RecipeRepository recipeRepository;
    public OtherImageRecipeAndRecipeSlimResponseDto addOtherImageRecipe(int recipeId, OtherImageRecipeRequestDto request) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new RuntimeException("Recipe not found"));
        OtherImageRecipe otherImageRecipe = OtherImageRecipe.builder()
                .img(request.getImg())
                .recipe(recipe)
                .build();
        return otherImageRecipeMapper.toOtherImageRecipeAndRecipeSlimResponseDto(otherImageRecipeRepository.save(otherImageRecipe));
    }
    public OtherImageRecipeAndRecipeSlimResponseDto getOtherImageRecipeById(int otherImageRecipeId) {
        return otherImageRecipeMapper.toOtherImageRecipeAndRecipeSlimResponseDto(otherImageRecipeRepository.findById(otherImageRecipeId).orElseThrow(()->new RuntimeException("Other Image not found")));
    }
    public Set<OtherImageRecipeAndRecipeSlimResponseDto> getOtherImageRecipeListByRecipeId(int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new RuntimeException("Recipe not found"));
        return otherImageRecipeMapper.toSetOtherImageRecipeAndRecipeSlimResponseDto(otherImageRecipeRepository.findOtherImageRecipeByRecipe(recipe));
    }
    public Set<OtherImageRecipeAndRecipeSlimResponseDto> getAllOtherImageRecipe() {
        return otherImageRecipeMapper.toSetOtherImageRecipeAndRecipeSlimResponseDto(otherImageRecipeRepository.findAll());
    }

    public OtherImageRecipeAndRecipeSlimResponseDto updateOtherImageRecipe(int otherImageRecipeId, OtherImageRecipeRequestDto request) {
        OtherImageRecipe otherImageRecipe = otherImageRecipeRepository.findById(otherImageRecipeId).orElseThrow(()->new RuntimeException("Other Image not found"));
        otherImageRecipe.setImg(request.getImg());
        return otherImageRecipeMapper.toOtherImageRecipeAndRecipeSlimResponseDto(otherImageRecipeRepository.save(otherImageRecipe));
    }

    public String deleteOtherImageRecipe(int otherImageRecipeId) {
        otherImageRecipeRepository.deleteById(otherImageRecipeId);
        return "Other Image Deleted";
    }
}
