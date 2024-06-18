package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.TopicMapper;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeSlimAndSetRecipeCardResponseDto;
import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import com.bodyupbe.bodyupbe.repository.TopicRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RecipeTopicService {
    TopicRepository topicRepository;
    RecipeRepository recipeRepository;
    TopicMapper topicMapper;

    public Set<TopicRecipeResponseSlimDto> getAllTopicRecipe() {
        List<Topic> topics = topicRepository.findByTopic("recipe");
        return topicMapper.toSetTopicRecipeResponseSlimDto(topics);
    }

    public TopicRecipeSlimAndSetRecipeCardResponseDto getRecipeByTopicId(int topicId, Optional<Integer> userId) {
        Topic topic = new Topic();

        if (topicId == -1) {
            topic.setId(-1);
            topic.setName("All Recipes");
            topic.setDescription("Try out all the recipes and make something new!");
            topic.setRecipes(new HashSet<>(recipeRepository.findAll()));
        } else if (topicId == 0) {
            topic.setId(0);
            topic.setName("Latest Recipes");
            topic.setDescription("Try out the latest recipes and make something new!");
            topic.setRecipes(new HashSet<>(recipeRepository.findByOrderByCreateAtDesc()));
        }
        else{
            topic = topicRepository.findById(topicId).orElseThrow(() -> new RuntimeException("Topic not found"));
        }
        TopicRecipeSlimAndSetRecipeCardResponseDto topicRecipeSlimAndSetRecipeCardResponseDto = topicMapper.toTopicRecipeSlimAndSetRecipeCardResponseDto(topic);

        userId.ifPresent(id -> {
            topicRecipeSlimAndSetRecipeCardResponseDto.getRecipes().forEach(recipe -> {
                recipe.setBookmarked(recipeRepository.findBookmarkedByUserIdAndRecipeId(id, recipe.getId()));
                recipe.setCurrentRating(recipeRepository.findRatingStarRecipeByUserId(id, recipe.getId())
                        .map(RatingRecipe::getStar)
                        .orElse(0));
            });
        });

        return topicRecipeSlimAndSetRecipeCardResponseDto;
    }


    public Set<TopicRecipeSlimAndSetRecipeCardResponseDto> getTopicRecipe() {
        List<Topic> topics = topicRepository.findByTopic("recipe");
        return topicMapper.toSetTopicRecipeSlimAndSetRecipeCardResponseDto(topics);
    }

    public Set<TopicRecipeSlimAndSetRecipeCardResponseDto> getTopic4Recipe(Optional<Integer> userId) {
        List<Topic> topics = topicRepository.findByTopic("recipe");
        Set<TopicRecipeSlimAndSetRecipeCardResponseDto> setTopicRecipeSlimAndSetRecipeCardResponseDto = topicMapper.toSetTopicRecipeSlimAndSetRecipeCardResponseDto(topics);

        if (userId.isPresent()) {
            setTopicRecipeSlimAndSetRecipeCardResponseDto.forEach(topic -> {
                topic.setRecipes(topic.getRecipes().stream().limit(4).collect(Collectors.toSet()));
                topic.getRecipes().forEach(recipe -> {
                    recipe.setBookmarked(recipeRepository.findBookmarkedByUserIdAndRecipeId(userId.get(), recipe.getId()));
                    Optional<RatingRecipe> ratingRecipe = recipeRepository.findRatingStarRecipeByUserId(userId.get(), recipe.getId());
                    if (ratingRecipe.isPresent()) {
                        recipe.setCurrentRating(ratingRecipe.get().getStar());
                    } else {
                        recipe.setCurrentRating(0);
                    }
                });
            });
        }
        return setTopicRecipeSlimAndSetRecipeCardResponseDto;
    }
}
