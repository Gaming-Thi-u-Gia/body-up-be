package com.bodyupbe.bodyupbe.dto.mapper;

import com.bodyupbe.bodyupbe.dto.request.recipe.NoteRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.request.recipe.TopicRecipeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseRecipeServiceDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.model.Topic;
import org.mapstruct.Mapper;

import java.util.Set;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    Set<TopicRecipeResponseSlimDto> topicRecipeResponseSlimDto(Set<Topic> topics);
    TopicRecipeResponseDto toEntity(NoteRecipeRequestDto request);
    com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseDto topicRecipeResponseDto(Topic topic);
    Set<TopicRecipeResponseRecipeServiceDto> topicRecipeResponseDtoList(Set<Topic> topics);
}
