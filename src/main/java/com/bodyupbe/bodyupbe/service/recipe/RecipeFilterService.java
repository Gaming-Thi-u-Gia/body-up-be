package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeFilterMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeFilterDto;
import com.bodyupbe.bodyupbe.exception.recipe.AppException;
import com.bodyupbe.bodyupbe.exception.recipe.ErrorCode;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.recipe.RecipeCategory;
import com.bodyupbe.bodyupbe.model.recipe.RecipeFilter;
import com.bodyupbe.bodyupbe.repository.RecipeCategoryRepository;
import com.bodyupbe.bodyupbe.repository.RecipeFilterRepository;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeFilterService {
    RecipeFilterRepository recipeFilterRepository;
    RecipeCategoryRepository recipeCategoryRepository;
    RecipeRepository recipeRepository;
    RecipeFilterMapper recipeFilterMapper;

    public RecipeFilterDto addRecipeFilter(int recipeCategoryId, int recipeId, RecipeFilterDto request) {
        RecipeCategory recipeCategory = recipeCategoryRepository.findById(recipeCategoryId).orElseThrow(()-> new AppException(ErrorCode.INVALID_ID));
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()-> new AppException(ErrorCode.INVALID_ID));
        RecipeFilter recipeFilter = recipeFilterMapper.toRecipeFilter(request);
        recipeFilter.setRecipeCategory(recipeCategory);
        recipeFilter.setRecipe(recipe);
        return recipeFilterMapper.toRecipeFilterDto(recipeFilterRepository.save(recipeFilter));
    }
    public RecipeFilterDto getRecipeFilterById(int recipeFilterId) {
        return recipeFilterMapper.toRecipeFilterDto(recipeFilterRepository.findById(recipeFilterId).orElseThrow(()-> new AppException(ErrorCode.INVALID_ID)));
    }
    public List<RecipeFilterDto> getRecipeFilters() {
        return recipeFilterRepository.findAll().stream().map(recipeFilter -> recipeFilterMapper.toRecipeFilterDto(recipeFilter)).collect(Collectors.toList());
    }
    public String deleteRecipeFilterById(int recipeFilterId) {
        RecipeFilter recipeFilter = recipeFilterRepository.findById(recipeFilterId).orElseThrow(()-> new AppException(ErrorCode.INVALID_ID));
        recipeFilterRepository.delete(recipeFilter);
        return "Recipe filter deleted with id: " + recipeFilterId;
    }

}
