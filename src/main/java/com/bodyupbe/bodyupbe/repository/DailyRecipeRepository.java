package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.recipe.DailyRecipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface DailyRecipeRepository extends JpaRepository<DailyRecipe, Integer> {

    @Query("SELECT dr FROM DailyRecipe dr " +
        "JOIN FETCH dr.recipe r " +
        "JOIN FETCH dr.dailyExercise de " +
        "JOIN de.workoutProgram wp " +
        "WHERE de.day = :day AND wp.id = :workoutProgramId")
    Set<DailyRecipe> findAllByDayAndWorkoutProgramId(@Param("day") String day, @Param("workoutProgramId") Integer workoutProgramId);
}
