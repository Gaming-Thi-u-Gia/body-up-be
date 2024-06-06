//package com.bodyupbe.bodyupbe.service.recipe;
//
//import com.bodyupbe.bodyupbe.dto.mapper.recipe.RatingRecipeMapper;
//import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
//import com.bodyupbe.bodyupbe.dto.mapper.user.UserMapper;
//import com.bodyupbe.bodyupbe.dto.request.recipe.RatingRecipeDto;
//import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
//import com.bodyupbe.bodyupbe.model.recipe.Recipe;
//import com.bodyupbe.bodyupbe.model.user.User;
//import com.bodyupbe.bodyupbe.repository.RatingRecipeRepository;
//import com.bodyupbe.bodyupbe.repository.RecipeRepository;
//import com.bodyupbe.bodyupbe.repository.UserRepository;
//import lombok.AccessLevel;
//import lombok.RequiredArgsConstructor;
//import lombok.experimental.FieldDefaults;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import java.util.Set;
//
//@Slf4j
//@Service
//@RequiredArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
//public class RatingRecipeService {
//    RatingRecipeRepository ratingRecipeRepository;
//    UserRepository userRepository;
//    RecipeRepository recipeRepository;
//    RatingRecipeMapper ratingRecipeMapper;
//    UserMapper userMapper;
//    RecipeMapper recipeMapper;
////    public RatingRecipeDto ratingRecipe(int recipeId, int userId, int star) {
////        User user = userRepository.findById(userId)
////                .orElseThrow(() -> new RuntimeException("User Not Found"));
////        Recipe recipe = recipeRepository.findById(recipeId)
////                .orElseThrow(() -> new RuntimeException("Recipe Not Found"));
////        RatingRecipe ratingRecipeObj = ratingRecipeRepository.findRatingByRecipeAndUser(recipe, user);
////        if (ratingRecipeObj == null) {
////            ratingRecipeObj = new RatingRecipe();
////            ratingRecipeObj.setRecipe(recipe);
////            ratingRecipeObj.setUser(user);
////        }
////        ratingRecipeObj.setStar(star);
////        RatingRecipe savedRating = ratingRecipeRepository.save(ratingRecipeObj);
////        updateAverageRating(recipe);
////        RatingRecipeDto ratingRecipeDto = ratingRecipeMapper.toRatingRecipeDto(savedRating);
////        ratingRecipeDto.setUserDto(userMapper.toUserDto(ratingRecipeObj.getUser()));
////        ratingRecipeDto.setRecipeDto(recipeMapper.toRecipeDto(ratingRecipeObj.getRecipe()));
////        return ratingRecipeDto;
////    }
////    public void updateAverageRating(Recipe recipe) {
////        int totalRating = 0;
////        Set<RatingRecipe> ratingRecipes = recipe.getRatingRecipes();
////        for (RatingRecipe ratingRecipe : ratingRecipes) {
////            totalRating += ratingRecipe.getStar();
////        }
////        double averageRatingRecipe = 0;
////        if (!ratingRecipes.isEmpty()) {
////            averageRatingRecipe = (double) totalRating / ratingRecipes.size();
////        }
////        recipe.setAverageRating(averageRatingRecipe);
////        recipeRepository.save(recipe);
////    }
//}
