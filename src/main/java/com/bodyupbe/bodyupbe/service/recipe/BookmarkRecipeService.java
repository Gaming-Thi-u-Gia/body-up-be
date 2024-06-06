package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.BookmarkRecipeMapper;
import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
import com.bodyupbe.bodyupbe.dto.mapper.user.UserMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.BookmarkRecipeDto;
import com.bodyupbe.bodyupbe.model.recipe.BookmarkRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.BookmarkRecipeRepository;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
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
public class BookmarkRecipeService {
    BookmarkRecipeRepository bookmarkRecipeRepository;
    UserRepository userRepository;
    RecipeRepository recipeRepository;
    BookmarkRecipeMapper bookmarkRecipeMapper;
    UserMapper userMapper;
    RecipeMapper recipeMapper;

    public BookmarkRecipeDto addBookmarkRecipe(int userId, int recipeId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() -> new RuntimeException("Recipe not found"));
        BookmarkRecipe bookmarkRecipe = BookmarkRecipe.builder()
                .user(user)
                .recipe(recipe)
                .build();
        BookmarkRecipe bookmarkRecipeResult= bookmarkRecipeRepository.save(bookmarkRecipe);
        BookmarkRecipeDto bookmarkRecipeResultDto= bookmarkRecipeMapper.toBookmarkRecipeDto(bookmarkRecipeResult);
        bookmarkRecipeResultDto.setUserDto(userMapper.toUserDto(user));
        bookmarkRecipeResultDto.setRecipeDto(recipeMapper.toRecipeDto(recipe));
        return bookmarkRecipeResultDto;
    }

    public BookmarkRecipeDto getBookmarkRecipeById(int id) {
        BookmarkRecipe bookmarkRecipe = bookmarkRecipeRepository.findById(id).orElseThrow(() -> new RuntimeException("BookmarkRecipe not found"));
        return bookmarkRecipeMapper.toBookmarkRecipeDto(bookmarkRecipe);
    }

    public List<BookmarkRecipeDto> getAllBookmarkRecipes() {
        return bookmarkRecipeRepository.findAll().stream()
                .map(bookmarkRecipe -> bookmarkRecipeMapper.toBookmarkRecipeDto(bookmarkRecipe)).collect(Collectors.toList());
    }

    public void deleteBookmarkRecipe(int id) {
        BookmarkRecipe bookmarkRecipe = bookmarkRecipeRepository.findById(id).orElseThrow(() -> new RuntimeException("BookmarkRecipe not found"));
        bookmarkRecipeRepository.delete(bookmarkRecipe);
    }
}
