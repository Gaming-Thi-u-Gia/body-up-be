package com.bodyupbe.bodyupbe.dto.mapper;

import com.bodyupbe.bodyupbe.dto.request.FeedbackRequestDto;
import com.bodyupbe.bodyupbe.dto.response.FeedbackWorkoutResponseDto;
import com.bodyupbe.bodyupbe.model.FeedbackWorkout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FeedBackMapper {
    FeedbackWorkout toFeedbackWorkout(FeedbackRequestDto feedbackWorkout);

    FeedbackWorkoutResponseDto toFeedbackWorkoutResponseDto(FeedbackWorkout feedbackWorkout);
}
