package com.bodyupbe.bodyupbe.controller.recipe;

//import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeDto;
//import com.bodyupbe.bodyupbe.dto.response.ApiReponse;
//import com.bodyupbe.bodyupbe.service.recipe.RecipeService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/recipe")
public class RecipeController {
//    RecipeService recipeService;
//    @PostMapping
//    public ApiReponse<RecipeDto> addRecipe(@RequestBody RecipeDto recipeDto) {
//        return ApiReponse.<RecipeDto>builder()
//                .result(recipeService.addRecipe(recipeDto))
//                .build();
//    }
}
