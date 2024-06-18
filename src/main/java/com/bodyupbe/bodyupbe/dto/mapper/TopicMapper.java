package com.bodyupbe.bodyupbe.dto.mapper;

import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeSlimAndSetRecipeCardResponseDto;
import com.bodyupbe.bodyupbe.model.Topic;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    Set<TopicRecipeResponseSlimDto> toSetTopicRecipeResponseSlimDto(List<Topic> topics);
    Set<TopicRecipeSlimAndSetRecipeCardResponseDto> toSetTopicRecipeSlimAndSetRecipeCardResponseDto(List<Topic> topic);
    TopicRecipeSlimAndSetRecipeCardResponseDto toTopicRecipeSlimAndSetRecipeCardResponseDto(Topic topic);
}
