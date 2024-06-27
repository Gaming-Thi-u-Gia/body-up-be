package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgram,Integer> {

    //find count workout program
    @Query("SELECT COUNT(w) FROM WorkoutProgram w")
    int countWorkoutProgram();

}
