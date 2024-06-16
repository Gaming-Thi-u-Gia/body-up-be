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

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :recipeName, '%'))")
    List<Recipe> findRecipeByNameContainingIgnoreCase(@Param("recipeName") String recipeName);

    Set<Recipe> findByOrderByCreateAtDesc();

    @Query("SELECT r FROM Recipe r JOIN r.recipeCategories rc WHERE rc.id IN :categoryIds")
    Set<Recipe> findByRecipeCategories_IdIn(@Param("categoryIds") Set<Integer> categoryIds);

    Set<Recipe> findTop2ByOrderByCreateAtDesc();

    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM Recipe r JOIN r.bookmarkUsers bu WHERE bu.id = :userId AND r.id = :recipeId")
    boolean findBookmarkedByUserIdAndRecipeId(int userId, int recipeId);

    @Query("SELECT COUNT(r) FROM RatingRecipe r WHERE r.recipe.id = :recipeId")
    int countRatingRecipesByRecipeId(@Param("recipeId") int recipeId);

    @Query("SELECT rr FROM Recipe r JOIN r.ratingRecipes rr WHERE rr.user.id = :userId AND r.id = :recipeId")
    Optional<RatingRecipe> findRatingStarRecipeByUserId(@Param("userId") int userId , @Param("recipeId") int recipeId);
        @Query("SELECT r FROM Recipe r WHERE r.id IN (" +
                "SELECT r1.id FROM Recipe r1 JOIN r1.recipeCategories c1 WHERE c1.id IN :categoryIds " +
                "GROUP BY r1.id HAVING COUNT(c1.id) = :categorySize)")

    Set<Recipe> findRecipesByCategoryIds(@Param("categoryIds") Set<Integer> categoryIds, @Param("categorySize") long categorySize);

}

