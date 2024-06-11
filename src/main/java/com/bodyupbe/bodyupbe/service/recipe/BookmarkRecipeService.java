package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
import com.bodyupbe.bodyupbe.dto.mapper.user.UserMapper;
import com.bodyupbe.bodyupbe.dto.response.recipe.BookmarkRecipeResponseBookmarkSlimDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserBookmarkRecipeDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookmarkRecipeService {
    RecipeRepository recipeRepository;
    UserRepository userRepository;
    UserMapper userMapper;
    RecipeMapper recipeMapper;
    public Optional<UserBookmarkRecipeDto> toggleBookmarkRecipe(int userId, int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if(user.getBookmarkRecipes().contains(recipe)) {
            user.getBookmarkRecipes().remove(recipe);
            recipe.getBookmarkUsers().remove(user);
            return Optional.of(userMapper.toUserBookmarkRecipeDto(userRepository.save(user)));
        }

        user.getBookmarkRecipes().add(recipe);
        recipe.getBookmarkUsers().add(user);
        return Optional.of(userMapper.toUserBookmarkRecipeDto(userRepository.save(user)));
    }


    public UserBookmarkRecipeDto getSetBookmarkRecipeByUserId(int userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return userMapper.toUserBookmarkRecipeDto(user);
    }
}
