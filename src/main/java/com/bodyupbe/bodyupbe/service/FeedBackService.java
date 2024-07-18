package com.bodyupbe.bodyupbe.service;

import com.bodyupbe.bodyupbe.dto.mapper.FeedBackMapper;
import com.bodyupbe.bodyupbe.dto.request.FeedbackRequestDto;
import com.bodyupbe.bodyupbe.dto.response.FeedbackWorkoutResponseDto;
import com.bodyupbe.bodyupbe.model.FeedbackWorkout;
import com.bodyupbe.bodyupbe.model.RatingWorkout;
import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.bodyupbe.bodyupbe.repository.FeedbackWorkoutRepository;
import com.bodyupbe.bodyupbe.repository.RatingWorkoutRepository;
import com.bodyupbe.bodyupbe.repository.WorkoutProgramRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FeedBackService {
    WorkoutProgramRepository workoutProgramRepository;
    FeedbackWorkoutRepository feedbackWorkoutRepository;
    FeedBackMapper feedBackMapper;
    RatingWorkoutRepository ratingWorkoutRepository;
    public FeedbackWorkoutResponseDto createFeedback(FeedbackRequestDto feedbackRequestDto, User user, int workoutProgramId) {
        WorkoutProgram workoutProgram = workoutProgramRepository.findById(workoutProgramId)
                .orElseThrow(() -> new RuntimeException("Workout Program not found"));

        // Map FeedbackRequestDto to FeedbackWorkout
        FeedbackWorkout feedbackWorkout = feedBackMapper.toFeedbackWorkout(feedbackRequestDto);

        // Set User and WorkoutProgram before saving FeedbackWorkout
        feedbackWorkout.setUser(user);
        feedbackWorkout.setWorkoutProgram(workoutProgram);

        // Create RatingWorkout and set its attributes
        RatingWorkout ratingWorkout = new RatingWorkout();
        ratingWorkout.setStar(feedbackRequestDto.getRatingWorkout().getStar());
        ratingWorkout.setFeedbackWorkout(feedbackWorkout);

        // Set RatingWorkout in FeedbackWorkout before saving
        feedbackWorkout.setRatingWorkout(ratingWorkout);

        // Save FeedbackWorkout, which will cascade the save to RatingWorkout due to CascadeType.ALL
        FeedbackWorkout savedFeedback = feedbackWorkoutRepository.save(feedbackWorkout);
        updateAverageRating(workoutProgram);
        // Convert saved FeedbackWorkout to FeedbackWorkoutResponseDto
        return feedBackMapper.toFeedbackWorkoutResponseDto(savedFeedback);
    }

    protected double updateAverageRating(WorkoutProgram workoutProgram) {
        int totalStar = 0;
        Set<FeedbackWorkout> ratingWorkouts = workoutProgram.getFeedbackWorkouts();
        for (FeedbackWorkout feedbackWorkout : ratingWorkouts) {
            totalStar += feedbackWorkout.getRatingWorkout().getStar();
        }
        double averageRatingWorkout = 0;
        if (!ratingWorkouts.isEmpty()) {
            averageRatingWorkout = (double) totalStar / ratingWorkouts.size();
        }
        workoutProgram.setAverageStar(averageRatingWorkout);

        return workoutProgramRepository.save(workoutProgram).getAverageStar();
    }
}
