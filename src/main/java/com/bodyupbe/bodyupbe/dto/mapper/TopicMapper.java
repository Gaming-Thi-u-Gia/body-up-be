package com.bodyupbe.bodyupbe.dto.mapper;

import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeSlimAndSetRecipeCardResponseDto;
import com.bodyupbe.bodyupbe.dto.request.TopicDto;
import com.bodyupbe.bodyupbe.dto.response.TopicResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.TopicWorkoutProgramResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_video.TopicVideoResponseDto;
import com.bodyupbe.bodyupbe.model.Topic;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    TopicResponseDto toTopicResponseDto(Topic topic);

    Topic toTopic(TopicDto topicDto);

    List<TopicResponseDto> toListTopicResponseDto(List<Topic> topics);

    Set<TopicResponseDto> toSetTopicResponseDto(Set<Topic> topics);

    Set<TopicResponseDto> toTopic(Set<Topic> topics);

    Set<TopicWorkoutProgramResponseDto> toTopicWorkoutProgram(Set<Topic> topics);
    Set<TopicWorkoutProgramResponseDto> toTopicWorkoutProgram(List<Topic> topics);

    Set<TopicVideoResponseDto> toTopicVideo(Set<Topic> topics);
    Set<TopicRecipeResponseSlimDto> toSetTopicRecipeResponseSlimDto(List<Topic> topics);
    Set<TopicRecipeSlimAndSetRecipeCardResponseDto> toSetTopicRecipeSlimAndSetRecipeCardResponseDto(List<Topic> topic);
    TopicRecipeSlimAndSetRecipeCardResponseDto toTopicRecipeSlimAndSetRecipeCardResponseDto(Topic topic);
}
