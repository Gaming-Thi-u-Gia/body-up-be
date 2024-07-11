package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
import com.bodyupbe.bodyupbe.dto.mapper.user.UserMapper;
import com.bodyupbe.bodyupbe.dto.response.recipe.BookmarkSlimResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserBookmarkRecipeResponseDto;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookmarkRecipeService {
    RecipeRepository recipeRepository;
    UserRepository userRepository;
    UserMapper userMapper;
    RecipeMapper recipeMapper;
    @Transactional
    public BookmarkSlimResponseDto toggleBookmarkRecipe(int userId, int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new IllegalArgumentException("Recipe not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        boolean bookmarked;
        if (user.getBookmarkRecipes().contains(recipe)) {
            removeBookmarkRecipe(user, recipe);
            bookmarked = false;
        } else {
            addBookmarkRecipe(user, recipe);
            bookmarked = true;
        }

        userRepository.save(user);
        return new BookmarkSlimResponseDto(recipeId, userId, bookmarked);
    }

    private void addBookmarkRecipe(User user, Recipe recipe) {
        user.getBookmarkRecipes().add(recipe);
        recipe.getBookmarkUsers().add(user);
    }

    private void removeBookmarkRecipe(User user, Recipe recipe) {
        user.getBookmarkRecipes().remove(recipe);
        recipe.getBookmarkUsers().remove(user);
    }


}
