package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeCategoryMapper;
import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeCategoryRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategoryResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.recipe.RecipeCategory;
import com.bodyupbe.bodyupbe.repository.RecipeCategoryRepository;
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
public class RecipeCategoryService {
    RecipeCategoryMapper recipeCategoryMapper;
    RecipeCategoryRepository recipeCategoryRepository;
    public RecipeCategoryResponseDto addRecipeCategory(RecipeCategoryRequestDto request){
        return recipeCategoryMapper.toResponseDto(recipeCategoryRepository.save(recipeCategoryMapper.toEntity(request)));
    }
    public RecipeCategoryResponseDto getRecipeCategoryById(int id){
        return recipeCategoryMapper.toResponseDto(recipeCategoryRepository.findById(id).orElse(null));
    }
    public List<RecipeCategoryResponseDto> getAllRecipeCategories(){
        return recipeCategoryMapper.toResponseDtoList(recipeCategoryRepository.findAll());
    }
    public RecipeCategoryResponseDto updateRecipeCategory(int recipeCategoryId,RecipeCategoryRequestDto request) {
        RecipeCategory recipeCategory = recipeCategoryRepository.findById(recipeCategoryId).orElseThrow(() -> new RuntimeException("Recipe category not found"));
        recipeCategory.setName(request.getName());
        return recipeCategoryMapper.toResponseDto(recipeCategoryRepository.save(recipeCategory));
    }
    public String deleteRecipeCategory(int recipeCategoryId){
        recipeCategoryRepository.deleteById(recipeCategoryId);
        return "Recipe category with id"+ recipeCategoryId +" deleted";
    }

}
