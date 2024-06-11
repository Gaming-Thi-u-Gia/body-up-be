package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.TopicMapper;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeResponseTopicRecipeDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseRecipeServiceDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import com.bodyupbe.bodyupbe.repository.TopicRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeTopicService {
    TopicRepository topicRepository;
    RecipeRepository recipeRepository;
    TopicMapper topicMapper;
    public Set<TopicRecipeResponseSlimDto> getAllTopicRecipe() {
        Set<Topic> topics = topicRepository.findByTopic("recipe");
        return topicMapper.topicRecipeResponseSlimDto(topics);
    }
    public TopicRecipeResponseDto getRecipeByTopicId(int topicId){
        Topic topic = topicRepository.findById(topicId).orElseThrow(() -> new RuntimeException("Topic not found"));
        return topicMapper.topicRecipeResponseDto(topic);
    }
    public Set<TopicRecipeResponseRecipeServiceDto> getRecipeByTopic(){
        Set<Topic> topics = topicRepository.findByTopic("recipe");
        return topicMapper.topicRecipeResponseDtoList(topics);
    }

}
