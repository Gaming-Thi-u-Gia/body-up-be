package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeCollectionMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeCollectionDto;
import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.recipe.RecipeCollection;
import com.bodyupbe.bodyupbe.repository.RecipeCollectionRepository;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import com.bodyupbe.bodyupbe.repository.TopicRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeCollectionService {
    RecipeCollectionRepository recipeCollectionRepository;
    RecipeRepository recipeRepository;
    TopicRepository topicRepository;
    RecipeCollectionMapper recipeCollectionMapper;
    public RecipeCollectionDto addRecipeCollection(int recipeId,int topicId ,RecipeCollectionDto recipeCollectionDto) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()->new RuntimeException("Recipe not found"));
        Topic topic = topicRepository.findById(topicId).orElseThrow(()->new RuntimeException("Topic not found"));
        RecipeCollection recipeCollection = recipeCollectionMapper.toRecipeCollection(recipeCollectionDto);
        recipeCollection.setRecipe(recipe);
        recipeCollection.setTopic(topic);
        return recipeCollectionMapper.toRecipeCollectionDto(recipeCollectionRepository.save(recipeCollection));
    }
    public RecipeCollectionDto getRecipeCollectionById(int recipeCollectionId) {
        return recipeCollectionMapper.toRecipeCollectionDto(recipeCollectionRepository.findById(recipeCollectionId).orElseThrow(()->new RuntimeException("Recipe collection not found")));
    }
    public List<RecipeCollectionDto> getAllRecipeCollections() {
        return recipeCollectionRepository.findAll().stream().map(recipeCollection -> recipeCollectionMapper.toRecipeCollectionDto(recipeCollection)).collect(Collectors.toList());
    }
    public void removeRecipeCollectionById(int recipeCollectionId) {
        recipeCollectionRepository.deleteById(recipeCollectionId);
    }
}
