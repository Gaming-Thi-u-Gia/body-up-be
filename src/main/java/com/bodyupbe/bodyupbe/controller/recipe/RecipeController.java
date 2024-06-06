package com.bodyupbe.bodyupbe.controller.recipe;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeDto;
import com.bodyupbe.bodyupbe.dto.response.ApiReponse;
import com.bodyupbe.bodyupbe.dto.response.ObjResponse;
import com.bodyupbe.bodyupbe.service.recipe.RecipeService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("api/v1/recipe")
public class RecipeController {
    RecipeService recipeService;
    @PostMapping("/add")
    public ApiReponse<RecipeDto> addRecipe(@RequestBody RecipeDto recipeDto) {
        return ApiReponse.<RecipeDto>builder()
                .result(recipeService.addRecipe(recipeDto))
                .build();
    }
    @GetMapping("/all")
    public ApiReponse<ObjResponse<RecipeDto>> getAllRecipes(@RequestParam int pageNo,@RequestParam int pageSize,@RequestParam String sortBy,@RequestParam String sortDir) {
        return ApiReponse.<ObjResponse<RecipeDto>>builder()
                .result(recipeService.getAllRecipes(pageNo,pageSize,sortBy,sortDir))
                .build();
    }

}
