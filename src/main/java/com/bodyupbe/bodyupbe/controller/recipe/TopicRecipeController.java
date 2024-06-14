package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeSlimAndSetRecipeSlimVsSetRecipeCategorySlimResponseDto;
import com.bodyupbe.bodyupbe.service.recipe.RecipeTopicService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/topic-recipe")
@RequiredArgsConstructor
@CrossOrigin
public class TopicRecipeController {
    RecipeTopicService topicRecipeService;
    @GetMapping("/all")
    public ResponseEntity<Set<TopicRecipeResponseSlimDto>> getAllTopicRecipe() {
        return ResponseEntity.ok(topicRecipeService.getAllTopicRecipe());
    }
    @GetMapping("/topicId")
    public ResponseEntity<TopicRecipeSlimAndSetRecipeSlimVsSetRecipeCategorySlimResponseDto> getRecipeByTopicId(int topicId){
        return ResponseEntity.ok(topicRecipeService.getRecipeByTopicId(topicId));
    }
    @GetMapping("/topic")
    public ResponseEntity<Set<TopicRecipeSlimAndSetRecipeSlimVsSetRecipeCategorySlimResponseDto>> getRecipeByTopic(@RequestParam Optional<Integer> userId){
        return ResponseEntity.ok(topicRecipeService.getTopic4Recipe(userId));
    }
}
