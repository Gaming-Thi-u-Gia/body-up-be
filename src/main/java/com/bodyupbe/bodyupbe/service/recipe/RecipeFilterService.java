package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeCategoryMapper;
import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.recipe.RecipeCategory;
import com.bodyupbe.bodyupbe.repository.RecipeCategoryRepository;
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
public class RecipeFilterService {
    RecipeRepository recipeRepository;
    RecipeCategoryRepository recipeCategoryRepository;
    RecipeMapper recipeMapper;
    RecipeCategoryMapper recipeCategoryMapper;

    public RecipeResponseDto assignCategoriesToRecipe(int recipeId, Set<Integer> categoryIds){
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found"));
        Set<RecipeCategory> recipeCategories = recipeCategoryRepository.findAllByIdIn(categoryIds);
        recipe.getRecipeCategories().addAll(recipeCategories);
        for(RecipeCategory recipeCategory : recipeCategories){
            recipeCategory.getRecipes().add(recipe);
        }
        return recipeMapper.toResponseDto(recipeRepository.save(recipe));
    }



    public RecipeResponseDto updateCategoriesToRecipe(int recipeId, Set<Integer> categoryIds){
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found"));
        Set<RecipeCategory> newCategories = recipeCategoryRepository.findAllByIdIn(categoryIds);
        for (RecipeCategory existingCategory : recipe.getRecipeCategories()) {
            existingCategory.getRecipes().remove(recipe);
        }
        recipe.getRecipeCategories().clear();
        recipe.getRecipeCategories().addAll(newCategories);

        for (RecipeCategory newCategory : newCategories) {
            newCategory.getRecipes().add(recipe);
        }
        return recipeMapper.toResponseDto(recipeRepository.save(recipe));
    }
    public Set<RecipeResponseDto> getAllRecipesByCategoryId(int categoryId){
        RecipeCategory recipeCategory = recipeCategoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Recipe category not found"));
        Set<Recipe> recipes = recipeCategory.getRecipes();
        return recipeMapper.toResponseDtoSet(recipes);
    }
    public Set<RecipeResponseDto> getAllBySetCategory(Set<Integer> recipeCategoryIds){
        Set<RecipeCategory> recipeCategories = recipeCategoryRepository.findAllByIdIn(recipeCategoryIds);
        Set<Recipe> recipes = recipeRepository.findByRecipeCategories_IdIn(recipeCategoryIds);
        return recipeMapper.toResponseDtoSet(recipes);
    }


}
