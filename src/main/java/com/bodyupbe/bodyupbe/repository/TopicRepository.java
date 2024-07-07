package com.bodyupbe.bodyupbe.repository;

import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCardResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeSlimAndSetRecipeCardResponseDto;
import com.bodyupbe.bodyupbe.model.Topic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopicRepository extends JpaRepository<Topic,Integer> {

    @Query("SELECT NEW com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeSlimAndSetRecipeCardResponseDto(t.id, t.topic, t.name, t.description,null) FROM Topic t WHERE t.topic = :topic")
    Page<TopicRecipeSlimAndSetRecipeCardResponseDto> findByTopic(@Param("topic") String topic, Pageable pageable);
    List<Topic> findByTopic(String topic);
    @Query("SELECT NEW com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCardResponseDto(r.id, r.name, r.avgStar, r.img, r.createAt) FROM Topic t JOIN t.recipes r WHERE t.id = :topicId")
    List<RecipeCardResponseDto> findRecipeCardResponseDtoByTopicId(@Param("topicId") int topicId, Pageable pageable);
}
