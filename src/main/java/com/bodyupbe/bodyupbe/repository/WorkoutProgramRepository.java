package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.WorkoutProgramCardResponseForAdminDto;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;

public interface WorkoutProgramRepository extends JpaRepository<WorkoutProgram,Integer> {

    //find count workout program
    @Query("SELECT COUNT(w) FROM WorkoutProgram w")
    int countWorkoutProgram();

    List<WorkoutProgram> findByNameContainingIgnoreCase(String name);

    @Query("SELECT w FROM WorkoutProgram w WHERE w.id IN (" +
            "SELECT w1.id FROM WorkoutProgram w1 JOIN w1.workoutProgramCategories c1 WHERE c1.id IN :categoryIds " +
            "GROUP BY w1.id HAVING COUNT(c1.id) = :categorySize)")
    Page<WorkoutProgram> findWorkoutProgramByCategoryIds(@Param("categoryIds") Set<Integer> categoryIds, @Param("categorySize") long categorySize, Pageable pageable);
    @Query("SELECT NEW com.bodyupbe.bodyupbe.dto.response.admin.dashboard.WorkoutProgramCardResponseForAdminDto(w.id,w.name,w.detail,w.day,w.equipment,w.type,w.time,w.year,w.img,w.banner,w.releaseDate,null,null) FROM WorkoutProgram w")
    Page<WorkoutProgramCardResponseForAdminDto> findWorkoutProgramCardResponseForAdminDto(Pageable pageable);
}
