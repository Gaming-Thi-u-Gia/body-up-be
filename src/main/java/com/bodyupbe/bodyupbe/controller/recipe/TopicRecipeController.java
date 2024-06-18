package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeSlimAndSetRecipeCardResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.recipe.RecipeTopicService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final UserRepository userRepository;

    @GetMapping("/all")
    public ResponseEntity<Set<TopicRecipeResponseSlimDto>> getAllTopicRecipe() {
        return ResponseEntity.ok(topicRecipeService.getAllTopicRecipe());
    }
    @GetMapping("/topicId")
    public ResponseEntity<TopicRecipeSlimAndSetRecipeCardResponseDto> getRecipeByTopicId(@RequestParam int topicId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        if(user.isPresent()){
            return ResponseEntity.ok(topicRecipeService.getRecipeByTopicId(topicId, Optional.of(user.get().getId())));
        }else{
            return ResponseEntity.ok(topicRecipeService.getRecipeByTopicId(topicId, Optional.empty()));
        }
    }
    @GetMapping("/topic")
    public ResponseEntity<Set<TopicRecipeSlimAndSetRecipeCardResponseDto>> getRecipeByTopic(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        if (user.isPresent()) {
            return ResponseEntity.ok(topicRecipeService.getTopic4Recipe(Optional.of(user.get().getId())));
        } else {
            return ResponseEntity.ok(topicRecipeService.getTopic4Recipe(Optional.empty()));
        }
    }
}
