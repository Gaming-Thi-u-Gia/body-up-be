package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.OtherImageRecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.OtherImageRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.OtherImageRecipeResponseDto;
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

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtherImageRecipeService {
    OtherImageRecipeRepository otherImageRecipeRepository;
    OtherImageRecipeMapper otherImageRecipeMapper;
    RecipeRepository recipeRepository;
    public OtherImageRecipeResponseDto addOtherImageRecipe(int recipeId,OtherImageRecipeRequestDto request) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new RuntimeException("Recipe not found"));
        OtherImageRecipe otherImageRecipe = OtherImageRecipe.builder()
                .img(request.getImg())
                .recipe(recipe)
                .build();
        return otherImageRecipeMapper.toResponseDto(otherImageRecipeRepository.save(otherImageRecipe));
    }
    public OtherImageRecipeResponseDto getOtherImageRecipeById(int otherImageRecipeId) {
        return otherImageRecipeMapper.toResponseDto(otherImageRecipeRepository.findById(otherImageRecipeId).orElseThrow(()->new RuntimeException("Other Image not found")));
    }
    public List<OtherImageRecipeResponseDto> getOtherImageRecipeListByRecipeId(int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new RuntimeException("Recipe not found"));
        return otherImageRecipeMapper.toResponseDtoList(otherImageRecipeRepository.findOtherImageRecipeByRecipe(recipe));
    }
    public List<OtherImageRecipeResponseDto> getAllOtherImageRecipe() {
        return otherImageRecipeMapper.toResponseDtoList(otherImageRecipeRepository.findAll());
    }

    public OtherImageRecipeResponseDto updateOtherImageRecipe(int otherImageRecipeId, OtherImageRecipeRequestDto request) {
        OtherImageRecipe otherImageRecipe = otherImageRecipeRepository.findById(otherImageRecipeId).orElseThrow(()->new RuntimeException("Other Image not found"));
        otherImageRecipe.setImg(request.getImg());
        return otherImageRecipeMapper.toResponseDto(otherImageRecipeRepository.save(otherImageRecipe));
    }

    public String deleteOtherImageRecipe(int otherImageRecipeId) {
        otherImageRecipeRepository.deleteById(otherImageRecipeId);
        return "Other Image Deleted";
    }
}
