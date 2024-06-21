package com.bodyupbe.bodyupbe.service.recipe;

import com.bodyupbe.bodyupbe.dto.mapper.TopicMapper;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCardResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeSlimAndSetRecipeCardResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectResponse;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectSetResponse;
import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.recipe.RatingRecipe;
import com.bodyupbe.bodyupbe.model.recipe.Recipe;
import com.bodyupbe.bodyupbe.repository.RecipeRepository;
import com.bodyupbe.bodyupbe.repository.TopicRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public ObjectResponse<TopicRecipeSlimAndSetRecipeCardResponseDto> getRecipeByTopicId(int topicId, Optional<Integer> userId, int pageNo, int pageSize) {
        Topic topic;
        Page<Recipe> pages;
        if (topicId == -1) {
            topic = new Topic();
            topic.setId(-1);
            topic.setName("All Recipes");
            topic.setDescription("Try out all the recipes and make something new!");
            pages = recipeRepository.findAll(PageRequest.of(pageNo, pageSize));
        } else if (topicId == 0) {
            topic = new Topic();
            topic.setId(0);
            topic.setName("Latest Recipes");
            topic.setDescription("Try out the latest recipes and make something new!");
            pages = recipeRepository.findByOrderByCreateAtDesc(PageRequest.of(pageNo, pageSize));
        } else {
            topic = topicRepository.findById(topicId).orElseThrow(() -> new IllegalArgumentException("Topic not found"));
            Pageable pageable = PageRequest.of(pageNo, pageSize);
            pages = topic.getRecipes().isEmpty() ? Page.empty() : recipeRepository.findByTopicId(topicId, pageable);
        }
        topic.setRecipes(new HashSet<>(pages.getContent()));
        TopicRecipeSlimAndSetRecipeCardResponseDto topicRecipeSlimAndSetRecipeCardResponseDto = topicMapper.toTopicRecipeSlimAndSetRecipeCardResponseDto(topic);
        if (userId.isPresent()) {
            for(RecipeCardResponseDto recipe : topicRecipeSlimAndSetRecipeCardResponseDto.getRecipes()) {
                recipe.setBookmarked(recipeRepository.findBookmarkedByUserIdAndRecipeId(userId.get(), recipe.getId()));
                Optional<RatingRecipe> ratingRecipe = recipeRepository.findRatingStarRecipeByUserId(userId.get(), recipe.getId());
                recipe.setCurrentRating(ratingRecipe.isPresent()?ratingRecipe.get().getStar():0);
            }
        }
        ObjectResponse<TopicRecipeSlimAndSetRecipeCardResponseDto> response = new ObjectResponse<>();
        response.setContent(topicRecipeSlimAndSetRecipeCardResponseDto);
        response.setTotalElements(pages.getTotalElements());
        response.setTotalPages(pages.getTotalPages());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());
        return response;
    }

    //da xu ly phan trang
    public ObjectSetResponse<TopicRecipeSlimAndSetRecipeCardResponseDto> getTopic4Recipe(Optional<Integer> userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<Topic> pages = topicRepository.findByTopic("recipe", pageable);
        List<Topic> topics = pages.getContent();
        Set<TopicRecipeSlimAndSetRecipeCardResponseDto> content = topicMapper.toSetTopicRecipeSlimAndSetRecipeCardResponseDto(topics);
        if (userId.isPresent()) {
            for(TopicRecipeSlimAndSetRecipeCardResponseDto topicRecipeSlimAndSetRecipeCardResponseDto : content) {
                for(RecipeCardResponseDto recipe : topicRecipeSlimAndSetRecipeCardResponseDto.getRecipes()) {
                    recipe.setBookmarked(recipeRepository.findBookmarkedByUserIdAndRecipeId(userId.get(), recipe.getId()));
                    Optional<RatingRecipe> ratingRecipe = recipeRepository.findRatingStarRecipeByUserId(userId.get(), recipe.getId());
                    recipe.setCurrentRating(ratingRecipe.isPresent()?ratingRecipe.get().getStar():0);
                }
            }
        }
        ObjectSetResponse<TopicRecipeSlimAndSetRecipeCardResponseDto> response = new ObjectSetResponse<>();
        response.setContent(content);
        response.setTotalElements(pages.getTotalElements());
        response.setTotalPages(pages.getTotalPages());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setTotalPages(pages.getTotalPages());
        response.setLast(pages.isLast());
        return response;
    }
}
