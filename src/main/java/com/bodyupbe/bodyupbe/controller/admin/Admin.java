package com.bodyupbe.bodyupbe.controller.admin;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.VideoRequestDto;
import com.bodyupbe.bodyupbe.service.recipe.RecipeService;
import com.bodyupbe.bodyupbe.service.video.VideoService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@CrossOrigin
public class Admin {
    VideoService videoService;
    RecipeService recipeService;
    @PostMapping("/create-video")
    public ResponseEntity<String> createVideo(@RequestBody VideoRequestDto videoRequestDto) {
        return ResponseEntity.ok(videoService.createVideo(videoRequestDto));
    }
    @PostMapping("/create-recipe")
    public ResponseEntity<String> addRecipe(@RequestBody RecipeRequestDto request) {
        return ResponseEntity.ok(recipeService.addRecipe(request));
    }
}
