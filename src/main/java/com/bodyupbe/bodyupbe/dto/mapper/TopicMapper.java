package com.bodyupbe.bodyupbe.dto.mapper;

import com.bodyupbe.bodyupbe.dto.request.recipe.NoteRecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.request.recipe.TopicRecipeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeSlimAndSetRecipeSlimVsSetRecipeCategorySlimResponseDto;
import com.bodyupbe.bodyupbe.model.Topic;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    Set<TopicRecipeResponseSlimDto> toSetTopicRecipeResponseSlimDto(List<Topic> topics);
    TopicRecipeResponseDto toTopicRecipeResponseDto(NoteRecipeRequestDto request);
    Set<TopicRecipeSlimAndSetRecipeSlimVsSetRecipeCategorySlimResponseDto> toSetTopicRecipeSlimAndSetRecipeSlimVsSetRecipeCategorySlimResponseDto(List<Topic> topic);
    TopicRecipeSlimAndSetRecipeSlimVsSetRecipeCategorySlimResponseDto toTopicRecipeSlimAndSetRecipeSlimVsSetRecipeCategorySlimResponseDto(Topic topic);

}
