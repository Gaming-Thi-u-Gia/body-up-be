package com.bodyupbe.bodyupbe.controller.admin;

import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.request.workout_program.WorkoutProgramRequestDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.VideoRequestDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.*;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectSetResponse;
import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import com.bodyupbe.bodyupbe.service.admin.AdminService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @DeleteMapping("/delete-recipe")
    public ResponseEntity<String> deleteRecipe(@RequestParam int recipeId) {
        return ResponseEntity.ok(adminService.deleteRecipe(recipeId));
    }
    @GetMapping("/list-user")
    ObjectSetResponse<UserSlimResponseDto> getListUser(@RequestParam(defaultValue = "0") int pageNo,@RequestParam(defaultValue = "10") int pageSize){
        return adminService.getListUser(pageNo, pageSize);
    }
    @GetMapping("/list-video")
    ObjectSetResponse<VideoCardResponseForAdminDto> getListVideo(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "12") int pageSize){
        return adminService.getListVideo(pageNo, pageSize);
    }
    @PutMapping("/update-video")
    public ResponseEntity<String> updateVideo(@RequestBody VideoRequestDto request) {
        return ResponseEntity.ok(adminService.updateVideo(request));
    }
    @DeleteMapping("/delete-video")
    public ResponseEntity<String> deleteVideo(@RequestParam int videoId) {
        return ResponseEntity.ok(adminService.deleteVideo(videoId));
    }
    @GetMapping("/list-post")
    ObjectSetResponse<PostCardResponseForAdminDto> getListPost(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        return adminService.getListPost(pageNo, pageSize);
    }
    @GetMapping("/post-detail")
    public ResponseEntity<PostResponseForAdminDto> getPostDetailForAdminById(@RequestParam int postId) {
        return ResponseEntity.ok(adminService.getPostDetailForAdminById(postId));
    }
    @DeleteMapping("/delete-post")
    public ResponseEntity<String> deletePost(@RequestParam int postId) {
        return ResponseEntity.ok(adminService.deletePost(postId));
    }
    @GetMapping("video-detail")
    public ResponseEntity<VideoResponseForAdminDto> getVideoDetailForAdminById(@RequestParam int videoId) {
        return ResponseEntity.ok(adminService.getVideoDetailForAdminById(videoId));
    }
    @GetMapping("/list-video-select")
    public List<VideoSelectForAdminResponseDto> getAllVideoSelectForAdmin() {
        return adminService.getAllVideoSelectForAdmin();
    }
    @GetMapping("/list-recipe-select")
    public List<RecipeSelectForAdminResponseDto> getAllRecipeSelectForAdmin() {
        return adminService.getAllRecipeSelectForAdmin();
    }
    @PostMapping("/create-program")
    public ResponseEntity<String> createProgram(@RequestBody WorkoutProgramRequestDto request) {
        return ResponseEntity.ok(adminService.addWorkoutProgram(request));
    }
    @DeleteMapping("/delete-workout-program")
    public ResponseEntity<String> deleteWorkoutProgram(@RequestParam int workoutProgramId) {
        return ResponseEntity.ok(adminService.deleteWorkoutProgram(workoutProgramId));
    }
    @GetMapping("/list-workout-program")
    ObjectSetResponse<WorkoutProgramCardResponseForAdminDto> getListWorkoutProgram(@RequestParam(defaultValue = "0") int pageNo, @RequestParam(defaultValue = "10") int pageSize){
        return adminService.getListWorkoutProgram(pageNo, pageSize);
    }
    @GetMapping("/workout-program-detail")
    public ResponseEntity<WorkoutProgramDetailForAdminDto> getWorkoutProgramDetailForAdminById(@RequestParam int workoutProgramId) {
        return ResponseEntity.ok(adminService.getWorkoutProgramDetailForAdminById(workoutProgramId));
    }
    @PutMapping("/update-workout-program")
    public ResponseEntity<String> updateWorkoutProgram(@RequestBody WorkoutProgramRequestDto request) {
        return ResponseEntity.ok(adminService.updateWorkoutProgram(request));
    }
    @GetMapping("/top3-completed-challenges")
    public List<TopUserChallengeResponseDto> getTop3UsersWithMostCompletedChallenges() {
        return adminService.getTop3UsersWithMostCompletedChallenges();
    }
}
