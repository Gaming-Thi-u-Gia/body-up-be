package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
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
public class RecipeService {
    RecipeMapper recipeMapper;
    RecipeRepository recipeRepository;
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
    public RecipeResponseDto getRecipeById(int recipeId){
        return recipeMapper.toRecipeResponseDto(recipeRepository.findById(recipeId).orElseThrow(()->
                new RuntimeException("Recipe not found")));
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
}
