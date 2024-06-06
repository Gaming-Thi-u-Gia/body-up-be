//package com.bodyupbe.bodyupbe.service.recipe;
//
//import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeCategoryMapper;
//import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeCategoryDto;
//import com.bodyupbe.bodyupbe.model.recipe.RecipeCategory;
//import com.bodyupbe.bodyupbe.repository.RecipeCategoryRepository;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class RecipeCategoryService {
//    RecipeCategoryRepository recipeCategoryRepository;
//    RecipeCategoryMapper recipeCategoryMapper;
//    public RecipeCategoryDto addRecipeCategory(RecipeCategoryDto request) {
//        RecipeCategory recipeCategory = recipeCategoryMapper.toRecipeCategory(request);
//        return recipeCategoryMapper.toRecipeCategoryDto(recipeCategoryRepository.save(recipeCategory));
//    }
//    public List<RecipeCategoryDto> getAllCategories() {
//        return recipeCategoryRepository.findAll().stream().map(recipeCategory -> recipeCategoryMapper.toRecipeCategoryDto(recipeCategory)).collect(Collectors.toList());
//    }
//    public RecipeCategoryDto getCategoryById(int id) {
//        return recipeCategoryMapper.toRecipeCategoryDto(recipeCategoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found")));
//    }
//    public RecipeCategoryDto updateCategoryById(int id, RecipeCategoryDto request) {
//        RecipeCategory recipeCategory = recipeCategoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
//        recipeCategory = RecipeCategory.builder()
//                .name(request.getName())
//                .type(request.getType())
//                .build();
//        return recipeCategoryMapper.toRecipeCategoryDto(recipeCategoryRepository.save(recipeCategory));
//    }
//    public void deleteCategoryById(int id) {
//        recipeCategoryRepository.deleteById(id);
//    }
//}
