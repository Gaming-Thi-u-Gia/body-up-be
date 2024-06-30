package com.bodyupbe.bodyupbe.controller.admin;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.VideoRequestDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.RecipeCardResponseForAdminDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.RecipeSlimResponseForAdminDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectSetResponse;
import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import com.bodyupbe.bodyupbe.service.admin.AdminService;
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
public class AdminController {
    AdminService adminService;
    @PostMapping("/create-video")
    public ResponseEntity<String> createVideo(@RequestBody VideoRequestDto videoRequestDto) {
        return ResponseEntity.ok(adminService.createVideo(videoRequestDto));
    }
    @PostMapping("/create-recipe")
    public ResponseEntity<String> addRecipe(@RequestBody RecipeRequestDto request) {
        return ResponseEntity.ok(adminService.addRecipe(request));
    }
    @GetMapping("/list-recipe")
    public ResponseEntity<ObjectSetResponse<RecipeCardResponseForAdminDto>> listRecipe(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "4") int pageSize) {
        return ResponseEntity.ok(adminService.getAllRecipeDetailForAdmin(pageNo,pageSize));
    }
    @PutMapping("/update-recipe")
    public ResponseEntity<String> updateRecipe(@RequestBody RecipeRequestDto request) {
        return ResponseEntity.ok(adminService.updateRecipe(request));
    }
    @GetMapping("/recipe-detail")
    public ResponseEntity<RecipeSlimResponseForAdminDto> getRecipeDetailForAdminById(@RequestParam int recipeId) {
        return ResponseEntity.ok(adminService.getRecipeDetailForAdminById(recipeId));
    }
    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteRecipe(@RequestParam int recipeId) {
        return ResponseEntity.ok(adminService.deleteRecipe(recipeId));
    }
    @GetMapping("/list-user")
    ObjectSetResponse<UserSlimResponseDto> getListUser(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "10") int pageSize){
        return adminService.getListUser(pageNo, pageSize);
    }
}
