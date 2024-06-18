    package com.bodyupbe.bodyupbe.service.recipe;

    import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeCategoryMapper;
    import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeCategoryRequestDto;
    import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategoryResponseSlimDto;
    import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategorySlimAndSetRecipeSlimResponseDto;
    import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCategoryTableResponseDto;
    import com.bodyupbe.bodyupbe.model.recipe.RecipeCategory;
    import com.bodyupbe.bodyupbe.repository.RecipeCategoryRepository;
    import lombok.AccessLevel;
    import lombok.RequiredArgsConstructor;
    import lombok.experimental.FieldDefaults;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.data.domain.PageRequest;
    import org.springframework.data.domain.Pageable;
    import org.springframework.stereotype.Service;

    import java.util.List;
    import java.util.Map;
    import java.util.Set;
    import java.util.stream.Collectors;

    @Slf4j
    @Service
    @RequiredArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
    public class RecipeCategoryService {
        RecipeCategoryMapper recipeCategoryMapper;
        RecipeCategoryRepository recipeCategoryRepository;
        public RecipeCategorySlimAndSetRecipeSlimResponseDto addRecipeCategory(RecipeCategoryRequestDto request){
            return recipeCategoryMapper.toRecipeCategorySlimAndSetRecipeSlimResponseDto(recipeCategoryRepository.save(recipeCategoryMapper.toRecipeCategory(request)));
        }
        public RecipeCategorySlimAndSetRecipeSlimResponseDto getRecipeCategoryById(int id){
            return recipeCategoryMapper.toRecipeCategorySlimAndSetRecipeSlimResponseDto(recipeCategoryRepository.findById(id).orElse(null));
        }
    //    public Set<RecipeCategorySlimAndSetRecipeSlimResponseDto> getAllRecipeCategories(){
    //        return recipeCategoryMapper.toSetRecipeCategorySlimAndSetRecipeSlimResponseDto(recipeCategoryRepository.findAll());
    //    }
        public RecipeCategorySlimAndSetRecipeSlimResponseDto updateRecipeCategory(int recipeCategoryId, RecipeCategoryRequestDto request) {
            RecipeCategory recipeCategory = recipeCategoryRepository.findById(recipeCategoryId).orElseThrow(() -> new RuntimeException("Recipe category not found"));
            recipeCategory.setName(request.getName());
            return recipeCategoryMapper.toRecipeCategorySlimAndSetRecipeSlimResponseDto(recipeCategoryRepository.save(recipeCategory));
        }
        public String deleteRecipeCategory(int recipeCategoryId){
            recipeCategoryRepository.deleteById(recipeCategoryId);
            return "Recipe category with id"+ recipeCategoryId +" deleted";
        }

        public Set<RecipeCategoryResponseSlimDto> getPopularCategory() {
            Pageable topFour = PageRequest.of(0, 4);
            List<RecipeCategory> topCategories = recipeCategoryRepository.findTop4CategoriesWithMostRecipes(topFour);
            return topCategories.stream().map(recipeCategoryResponseDto ->{
                RecipeCategoryResponseSlimDto recipeCategoryResponseSlimDto = new RecipeCategoryResponseSlimDto();
                recipeCategoryResponseSlimDto.setId(recipeCategoryResponseDto   .getId());
                recipeCategoryResponseSlimDto.setName(recipeCategoryResponseDto.getName());
                recipeCategoryResponseSlimDto.setType(recipeCategoryResponseDto.getType());
                recipeCategoryResponseSlimDto.setImg(recipeCategoryResponseDto.getImg());
                recipeCategoryResponseSlimDto.setTotalRecipe(recipeCategoryResponseDto.getRecipes().size());
                return recipeCategoryResponseSlimDto;
            }).collect(Collectors.toSet());
        };
        public Set<RecipeCategoryTableResponseDto> getAllRecipeCategoriesWithEachType() {
            List<RecipeCategory> categories = recipeCategoryRepository.findAll();
            List<RecipeCategoryResponseSlimDto> categoryDtoList = categories.stream()
                    .map(category -> recipeCategoryMapper.toRecipeCategoryResponseSlimDto(category))
                    .toList();
            Map<String, Set<RecipeCategoryResponseSlimDto>> groupedByType = categoryDtoList.stream()
                    .collect(Collectors.groupingBy(
                            recipeCategoryResponseSlimDto -> recipeCategoryResponseSlimDto.getType(),
                            Collectors.toSet()
                ));
        Set<RecipeCategoryTableResponseDto> responseDtoSet = groupedByType.entrySet().stream()
                .map(entry -> {
                    RecipeCategoryTableResponseDto responseDto = new RecipeCategoryTableResponseDto();
                    responseDto.setType(entry.getKey());
                    responseDto.setRecipeCategories(entry.getValue());
                    return responseDto;
                })
                .collect(Collectors.toSet());
        return responseDtoSet;
    }
}
