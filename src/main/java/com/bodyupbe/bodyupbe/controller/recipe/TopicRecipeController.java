package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeResponseSlimDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.TopicRecipeSlimAndSetRecipeCardResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectResponse;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectSetResponse;
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
    public ResponseEntity<ObjectResponse<TopicRecipeSlimAndSetRecipeCardResponseDto>> getRecipeByTopicId(@RequestParam(defaultValue = "0") int topicId, @RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "0") int pageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.ok(topicRecipeService.getRecipeByTopicId(topicId, user.isPresent()?Optional.of(user.get().getId()):Optional.empty(),pageNo,pageSize));
    }
    @GetMapping("/topic")
    public ResponseEntity<ObjectSetResponse<TopicRecipeSlimAndSetRecipeCardResponseDto>> getTopic4Recipe(@RequestParam(defaultValue = "0") int pageNo  , @RequestParam(defaultValue = "2") int pageSize){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        Optional<User> user = userRepository.findByEmail(currentPrincipalName);
        return ResponseEntity.ok(topicRecipeService.getTopic4Recipe(user.isPresent()?Optional.of(user.get().getId()):Optional.empty(),pageNo,pageSize));
    }
}
