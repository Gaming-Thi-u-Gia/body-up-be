package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe,Integer> {
    List<Recipe> findRecipeByName(String name);
    @Query("SELECT r FROM Recipe r JOIN r.recipeCategories rc WHERE rc.id IN :categoryIds")
    Set<Recipe> findByRecipeCategories_IdIn(@Param("categoryIds") Set<Integer> categoryIds);
    Set<Recipe> findTop2ByOrderByCreateAtDesc();
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Recipe r JOIN r.bookmarkUsers bu WHERE bu.id = :userId AND r.id = :recipeId")
    boolean findBookmarkedByUserIdAndRecipeId(int userId, int recipeId);
    @Query("SELECT COUNT(r) FROM RatingRecipe r WHERE r.recipe.id = :recipeId")
    int countRatingRecipesByRecipeId(@Param("recipeId") int recipeId);
    @Query("SELECT rr FROM Recipe r JOIN r.ratingRecipes rr WHERE rr.user.id = :userId AND r.id = :recipeId")
    Optional<RatingRecipe> findRatingStarRecipeByUserId(@Param("userId") int userId , @Param("recipeId") int recipeId);
}

