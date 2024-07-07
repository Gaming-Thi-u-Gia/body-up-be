package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeCategoryMapper;
import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.*;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectResponse;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectSetResponse;
import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.recipe.*;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeService {
    RecipeMapper recipeMapper;
    RecipeRepository recipeRepository;
    UserRepository userRepository;
    private final RatingRecipeRepository ratingRecipeRepository;
    RecipeCategoryMapper recipeCategoryMapper;
    private final RecipeCategoryRepository recipeCategoryRepository;
    TopicRepository topicRepository;
    IngredientRecipeRepository ingredientRecipeRepository;
    OtherImageRecipeRepository otherImageRecipeRepository;
    NoteRecipeRepository noteRecipeRepository;



    //    public RecipeResponseDto getRecipe(int id) {
//        return recipeMapper.toRecipeResponseDto(recipeRepository.findById(id).orElseThrow(() ->
//                new RuntimeException("Recipe not found")));
//    }
public Set<RecipeResponseDto> getAllRecipe() {
    return recipeMapper.toSetRecipeResponseDto(recipeRepository.findAll());
}
//    public RecipeResponseDto updateRecipe(int recipeId, RecipeRequestDto request) {
//        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() ->
//                new RuntimeException("Recipe not found"));
//        recipe.setName(request.getName());
//        recipe.setAvgStar(request.getAvgStar());
//        recipe.setPrepTime(request.getPrepTime());
//        recipe.setCookTime(request.getCookTime());
//        return recipeMapper.toRecipeResponseDto(recipeRepository.save(recipe));
//    }

//home
    public Set<RecipeLatestResponseDto> getLatestRecipe(Optional<Integer> userId) {
        Set<Recipe> recipes = recipeRepository.findTop2ByOrderByCreateAtDesc();
        Set<RecipeLatestResponseDto> setRecipeLatestResponseDto = recipeMapper.toSetRecipeLatestResponseDto(recipes);
        if (userId.isPresent()) {
            for (RecipeLatestResponseDto recipeLatestResponseDto : setRecipeLatestResponseDto) {
                recipeLatestResponseDto.setBookmarked(recipeRepository.findBookmarkedByUserIdAndRecipeId(userId.get(), recipeLatestResponseDto.getId()));
                Optional<RatingRecipe> ratingRecipe = recipeRepository.findRatingStarRecipeByUserId(userId.get(), recipeLatestResponseDto.getId());
                recipeLatestResponseDto.setCurrentRating(ratingRecipe.isPresent() ? ratingRecipe.get().getStar() : 0);
                log.info("ratingRecipe: " + recipeLatestResponseDto);
            }
        }
        return setRecipeLatestResponseDto;
    }

    //detail
    public RecipeDetailResponseDto getRecipeById(int recipeId, Optional<Integer> userId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() ->
                new RuntimeException("Recipe not found"));

        RecipeDetailResponseDto recipeDetailResponseDto = recipeMapper.toRecipeDetailResponseDto(recipe);
        if (userId.isPresent()) {
            boolean isBookmarked = recipeRepository.findBookmarkedByUserIdAndRecipeId(userId.get(), recipeDetailResponseDto.getId());
            recipeDetailResponseDto.setBookmarked(isBookmarked);
            Optional<RatingRecipe> ratingRecipe = recipeRepository.findRatingStarRecipeByUserId(userId.get(), recipeDetailResponseDto.getId());
            recipeDetailResponseDto.setCurrentRating(ratingRecipe.isPresent() ? ratingRecipe.get().getStar() : 0);
        }
        int totalRating = recipeRepository.countRatingRecipesByRecipeId(recipeId);
        recipeDetailResponseDto.setTotalRating(totalRating);
        return recipeDetailResponseDto;
    }

    //saved
    public ObjectSetResponse<RecipeCardResponseDto> getAllBookmarkedRecipe(int userId, int pageNo, int pageSize) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Recipe> pages = recipeRepository.findBookmarkedRecipesByUserId(userId, pageable);
        List<Recipe> recipes = pages.getContent();
        Set<RecipeCardResponseDto> content = recipeMapper.toSetRecipeCardResponseDto(recipes);
        for (RecipeCardResponseDto recipeCardResponseDto : content) {
            recipeCardResponseDto.setBookmarked(true);
            Optional<RatingRecipe> ratingRecipe = recipeRepository.findRatingStarRecipeByUserId(userId, recipeCardResponseDto.getId());
            recipeCardResponseDto.setCurrentRating(ratingRecipe.isPresent() ? ratingRecipe.get().getStar() : 0);
        }
        ObjectSetResponse<RecipeCardResponseDto> response = new ObjectSetResponse<>();
        response.setContent(content);
        response.setTotalElements(pages.getTotalElements());
        response.setTotalPages(pages.getTotalPages());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setTotalPages(pages.getTotalPages());
        response.setLast(pages.isLast());
        return response;
    }

    //search
    public ObjectSetResponse<RecipeCardResponseDto> getRecipeByName(String nameRecipe, Optional<Integer> userId, int pageNo, int pageSize) {
        RecipeCardSearchResponseDto recipeCardSearchResponseDto = new RecipeCardSearchResponseDto();
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Recipe> pages = recipeRepository.findRecipeByNameContainingIgnoreCase(nameRecipe, pageable);
        List<Recipe> recipes = pages.getContent();
        Set<RecipeCardResponseDto> content = recipeMapper.toSetRecipeCardResponseDto(recipes);
        if (userId.isPresent()) {
            for (RecipeCardResponseDto recipeCardResponseDto : content) {
                recipeCardResponseDto.setBookmarked(recipeRepository.findBookmarkedByUserIdAndRecipeId(userId.get(), recipeCardResponseDto.getId()));
                Optional<RatingRecipe> ratingRecipe = recipeRepository.findRatingStarRecipeByUserId(userId.get(), recipeCardResponseDto.getId());
                recipeCardResponseDto.setCurrentRating(ratingRecipe.isPresent() ? ratingRecipe.get().getStar() : 0);
            }
        }
        ObjectSetResponse<RecipeCardResponseDto> response = new ObjectSetResponse<>();
        response.setContent(content);
        response.setTotalElements(pages.getTotalElements());
        response.setTotalPages(pages.getTotalPages());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setTotalPages(pages.getTotalPages());
        response.setLast(pages.isLast());
        return response;
    }

    //filter
    public ObjectResponse<RecipeFilterResponseDto> getRecipeByCategory(Set<Integer> categoryIds, Optional<Integer> userId, int pageNo, int pageSize) {
        RecipeFilterResponseDto content = new RecipeFilterResponseDto();

        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Recipe> pages = recipeRepository.findRecipesByCategoryIds(categoryIds, categoryIds.size(), pageable);
        List<Recipe> recipes = pages.getContent();
        Set<RecipeCardResponseDto> setRecipeCardResponseDto = recipeMapper.toSetRecipeCardResponseDto(recipes);
        if (userId.isPresent()) {
            for (RecipeCardResponseDto recipeCardResponseDto : setRecipeCardResponseDto) {
                recipeCardResponseDto.setBookmarked(recipeRepository.findBookmarkedByUserIdAndRecipeId(userId.get(), recipeCardResponseDto.getId()));
                Optional<RatingRecipe> ratingRecipe = recipeRepository.findRatingStarRecipeByUserId(userId.get(), recipeCardResponseDto.getId());
                recipeCardResponseDto.setCurrentRating(ratingRecipe.isPresent() ? ratingRecipe.get().getStar() : 0);
            }
        }
        content.setRecipes(setRecipeCardResponseDto);
        content.setRecipeCategories(recipeCategoryMapper.toSetRecipeCategoryResponseSlimDto(recipeCategoryRepository.findAllByIdIn(categoryIds)));

        ObjectResponse<RecipeFilterResponseDto> response = new ObjectResponse<>();
        response.setContent(content);
        response.setTotalElements(pages.getTotalElements());
        response.setTotalPages(pages.getTotalPages());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());
        return response;
    }

}
