//package com.bodyupbe.bodyupbe.service.recipe;
//
//import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
//import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeDto;
//import com.bodyupbe.bodyupbe.dto.response.ObjResponse;
//import com.bodyupbe.bodyupbe.exception.recipe.AppException;
//import com.bodyupbe.bodyupbe.exception.recipe.ErrorCode;
//import com.bodyupbe.bodyupbe.model.recipe.Recipe;
//import com.bodyupbe.bodyupbe.repository.RecipeRepository;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import lombok.extern.slf4j.Slf4j;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.PageRequest;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.domain.Sort;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class RecipeService {
//    private static final Logger log = LoggerFactory.getLogger(RecipeService.class);
//    RecipeRepository recipeRepository;
//    RecipeMapper recipeMapper;
//
//    public RecipeDto addRecipe(RecipeDto recipeDto) {
//        return  recipeMapper.toRecipeDto(recipeRepository.save(recipeMapper.toRecipe(recipeDto)));
//    }
//    public ObjResponse<RecipeDto> getAllRecipes(int pageNo, int pageSize, String sortBy, String sortDir) {
//        log.info("getAllRecipes");
//        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
//                ? Sort.by(sortBy).ascending()
//                : Sort.by(sortBy).descending();
//        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
//        Page<Recipe> pages = recipeRepository.findAll(pageable);
//        List<Recipe> recipes = pages.getContent();
//        List<RecipeDto> content = recipeRepository.findAll().stream().map(recipe->recipeMapper.toRecipeDto(recipe)).collect(Collectors.toList());
//        ObjResponse<RecipeDto> response = new ObjResponse<>();
//        response.setContent(content);
//        response.setTotalPages(pages.getTotalPages());
//        response.setPageNo(pages.getNumber());
//        response.setPageSize(pages.getSize());
//        response.setLast(pages.isLast());
//        return response;
//    }
//
//    public RecipeDto getRecipeById(int id) {
//        return recipeMapper.toRecipeDto(recipeRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.USER_NOT_EXISTED)));
//    }
//
//    public RecipeDto updateRecipe(int userId, RecipeDto request) {
//        Recipe recipe = recipeRepository.findById(userId).orElseThrow(()->new AppException(ErrorCode.INVALID_ID));
//        recipe = Recipe.builder()
//                .name(request.getName())
//                .prepTime(request.getPrepTime())
//                .cookTime(request.getCookTime())
//                .img(request.getImg())
//                .cookDetail(request.getCookDetail())
//                .build();
//        return recipeMapper.toRecipeDto(recipeRepository.save(recipe));
//    }
//
//    public String deleteRecipe(int id) {
//        Recipe recipe = recipeRepository.findById(id).orElseThrow(()->new AppException(ErrorCode.INVALID_ID));
//        recipeRepository.delete(recipe);
//        return recipe.getName()+" deleted";
//    }
//}
