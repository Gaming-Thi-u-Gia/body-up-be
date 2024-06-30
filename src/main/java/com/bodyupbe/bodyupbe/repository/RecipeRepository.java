package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.RecipeCardResponseForAdminDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.RecipeSlimResponseForAdminDto;
import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import lombok.NonNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe,Integer> {

    @Query(value = "SELECT COUNT(*) FROM Recipe r")
    int countRecipe();

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.name) LIKE LOWER(CONCAT('%', :recipeName, '%')) ORDER BY r.createAt DESC")
    Page<Recipe> findRecipeByNameContainingIgnoreCase(@Param("recipeName") String recipeName,Pageable pageable);

    Page<Recipe> findByOrderByCreateAtDesc(Pageable pageable);

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
                "GROUP BY r1.id HAVING COUNT(c1.id) = :categorySize)" +
            "ORDER BY r.createAt DESC")
    Page<Recipe> findRecipesByCategoryIds(@Param("categoryIds") Set<Integer> categoryIds, @Param("categorySize") long categorySize,Pageable pageable);
    @Query("SELECT r FROM Recipe r JOIN r.recipeTopics t WHERE t.id = :topicId ORDER BY r.createAt DESC")
    Page<Recipe> findByTopicId(@Param("topicId") int topicId, Pageable pageable);

    @Query("SELECT r FROM Recipe r JOIN r.bookmarkUsers bu WHERE bu.id = :userId ORDER BY r.createAt DESC")
    Page<Recipe> findBookmarkedRecipesByUserId(int userId, Pageable pageable);
    @NonNull
    @Query("SELECT new com.bodyupbe.bodyupbe.dto.response.admin.dashboard.RecipeCardResponseForAdminDto(r.id, r.name, r.detail, r.avgStar,r.img) FROM Recipe r ORDER BY r.createAt DESC")
    Page<RecipeCardResponseForAdminDto> findAllSlim(Pageable pageable);


}

