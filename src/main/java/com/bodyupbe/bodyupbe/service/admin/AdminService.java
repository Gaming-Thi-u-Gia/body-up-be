package com.bodyupbe.bodyupbe.service.admin;

import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
import com.bodyupbe.bodyupbe.dto.mapper.video.VideoMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.VideoRequestDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.RecipeCardResponseForAdminDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.RecipeSlimResponseForAdminDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectSetResponse;
import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.recipe.*;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.bodyupbe.bodyupbe.model.workout_video.VideoCategory;
import com.bodyupbe.bodyupbe.repository.*;
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
import java.util.Set;
import java.util.stream.Collectors;
@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdminService {
    RecipeMapper recipeMapper;
    RecipeRepository recipeRepository;
    UserRepository userRepository;
    RecipeCategoryRepository recipeCategoryRepository;
    TopicRepository topicRepository;
    IngredientRecipeRepository ingredientRecipeRepository;
    OtherImageRecipeRepository otherImageRecipeRepository;
    NoteRecipeRepository noteRecipeRepository;
    VideoMapper videoMapper;
    VideoRepository videoRepository;
    VideoCategoryRepository videoCategoryRepository;
    public String createVideo(VideoRequestDto request) {
        Video video = videoMapper.toVideo(request);
        Set<VideoCategory> categories = request.getVideoCategories().stream()
                .map(categoryRequest -> videoCategoryRepository.findById(categoryRequest.getId())
                        .orElseThrow(() -> new RuntimeException("Video Category  not found: " + categoryRequest.getId())))
                .collect(Collectors.toSet());
        Set<Topic> topics = request.getVideoTopics().stream()
                .map(topicRequest -> topicRepository.findById(topicRequest.getId())
                        .orElseThrow(() -> new RuntimeException("Recipe Topic not found: " + topicRequest.getId())))
                .collect(Collectors.toSet());

        video.setVideoCategories(categories);
        video.setVideoTopics(topics);
        Video savedVideo = videoRepository.save(video);
        return "Add New Video Successfully With Video ID: " + savedVideo.getId();
    }

    public String addRecipe(RecipeRequestDto request) {
        Recipe recipe = recipeMapper.toRecipe(request);

        Set<RecipeCategory> categories = request.getRecipeCategories().stream()
                .map(categoryRequest -> recipeCategoryRepository.findById(categoryRequest.getId())
                        .orElseThrow(() -> new RuntimeException("RecipeCategory not found: " + categoryRequest.getId())))
                .collect(Collectors.toSet());
        Set<Topic> topics = request.getRecipeTopics().stream()
                .map(topicRequest -> topicRepository.findById(topicRequest.getId())
                        .orElseThrow(() -> new RuntimeException("Recipe Topic not found: " + topicRequest.getId())))
                .collect(Collectors.toSet());

        recipe.setRecipeCategories(categories);
        recipe.setRecipeTopics(topics);
        Recipe savedRecipe = recipeRepository.save(recipe);

        Set<IngredientRecipe> ingredientRecipes = request.getIngredientRecipes().stream()
                .map(ingredientRequest -> {
                    IngredientRecipe ingredient = new IngredientRecipe();
                    ingredient.setAmount(ingredientRequest.getAmount());
                    ingredient.setName(ingredientRequest.getName());
                    ingredient.setRecipe(savedRecipe);
                    return ingredient;
                })
                .collect(Collectors.toSet());
        ingredientRecipeRepository.saveAll(ingredientRecipes);

        Set<OtherImageRecipe> otherImageRecipes = request.getOtherImageRecipes().stream()
                .map(otherImageRequest -> {
                    OtherImageRecipe otherImageRecipe = new OtherImageRecipe();
                    otherImageRecipe.setImg(otherImageRequest.getImg());
                    otherImageRecipe.setRecipe(savedRecipe);
                    return otherImageRecipe;
                })
                .collect(Collectors.toSet());
        otherImageRecipeRepository.saveAll(otherImageRecipes);

        Set<NoteRecipe> noteRecipes = request.getNoteRecipes().stream()
                .map(noteRecipeRequest -> {
                    NoteRecipe noteRecipe = new NoteRecipe();
                    noteRecipe.setDetail(noteRecipeRequest.getDetail());
                    noteRecipe.setRecipe(savedRecipe);
                    return noteRecipe;
                })
                .collect(Collectors.toSet());
        noteRecipeRepository.saveAll(noteRecipes);

        savedRecipe.setNoteRecipes(noteRecipes);
        savedRecipe.setOtherImageRecipes(otherImageRecipes);
        savedRecipe.setIngredientRecipes(ingredientRecipes);
        recipeRepository.save(savedRecipe);
        return "Add New Recipe Successfully With Recipe ID: " + savedRecipe.getId();
    }
    public ObjectSetResponse<RecipeCardResponseForAdminDto> getAllRecipeDetailForAdmin(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<RecipeCardResponseForAdminDto> pages = recipeRepository.findAllSlim(pageable);
        List<RecipeCardResponseForAdminDto> content = pages.getContent();
        ObjectSetResponse<RecipeCardResponseForAdminDto> response = new ObjectSetResponse<>();
        response.setContent(recipeMapper.toRecipeCardResponseForAdminDto(content));
        response.setTotalPages(pages.getTotalPages());
        response.setTotalElements(pages.getTotalElements());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());
        return response;
    }
    public String updateRecipe(RecipeRequestDto request) {
        log.info("Update " + request);
        Recipe recipe = recipeRepository.findById(request.getId()).orElseThrow(() ->
                new RuntimeException("Recipe not found"));
        Set<RecipeCategory> categories = request.getRecipeCategories().stream()
                .map(categoryRequest -> recipeCategoryRepository.findById(categoryRequest.getId())
                        .orElseThrow(() -> new RuntimeException("RecipeCategory not found: " + categoryRequest.getId())))
                .collect(Collectors.toSet());
        Set<Topic> topics = request.getRecipeTopics().stream()
                .map(topicRequest -> topicRepository.findById(topicRequest.getId())
                        .orElseThrow(() -> new RuntimeException("Recipe Topic not found: " + topicRequest.getId())))
                .collect(Collectors.toSet());
        recipe.setRecipeCategories(categories);
        recipe.setRecipeTopics(topics);
        Recipe savedRecipe = recipeRepository.save(recipe);

        Set<IngredientRecipe> ingredientRecipes = request.getIngredientRecipes().stream()
                .map(ingredientRequest -> {
                    IngredientRecipe ingredient = new IngredientRecipe();
                    ingredient.setAmount(ingredientRequest.getAmount());
                    ingredient.setName(ingredientRequest.getName());
                    ingredient.setRecipe(savedRecipe);
                    return ingredient;
                })
                .collect(Collectors.toSet());
        ingredientRecipeRepository.saveAll(ingredientRecipes);

        Set<OtherImageRecipe> otherImageRecipes = request.getOtherImageRecipes().stream()
                .map(otherImageRequest -> {
                    OtherImageRecipe otherImageRecipe = new OtherImageRecipe();
                    otherImageRecipe.setImg(otherImageRequest.getImg());
                    otherImageRecipe.setRecipe(savedRecipe);
                    return otherImageRecipe;
                })
                .collect(Collectors.toSet());
        otherImageRecipeRepository.saveAll(otherImageRecipes);

        Set<NoteRecipe> noteRecipes = request.getNoteRecipes().stream()
                .map(noteRecipeRequest -> {
                    NoteRecipe noteRecipe = new NoteRecipe();
                    noteRecipe.setDetail(noteRecipeRequest.getDetail());
                    noteRecipe.setRecipe(savedRecipe);
                    return noteRecipe;
                })
                .collect(Collectors.toSet());
        noteRecipeRepository.saveAll(noteRecipes);
        savedRecipe.setNoteRecipes(noteRecipes);
        savedRecipe.setOtherImageRecipes(otherImageRecipes);
        savedRecipe.setIngredientRecipes(ingredientRecipes);
        savedRecipe.setName(request.getName());
        savedRecipe.setImg(request.getImg());
        savedRecipe.setPrepTime(request.getPrepTime());
        savedRecipe.setCookTime(request.getCookTime());
        savedRecipe.setCookingInstruction(request.getCookingInstruction());
        savedRecipe.setDetail(request.getDetail());
        recipeRepository.save(savedRecipe);
        return "Update Recipe Successfully With Recipe ID: " + savedRecipe.getId();
    }
    public RecipeSlimResponseForAdminDto getRecipeDetailForAdminById(int recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(() ->
                new RuntimeException("Recipe not found"));
        RecipeSlimResponseForAdminDto response = recipeMapper.toRecipeSlimResponseForAdminDto(recipe);
        response.setTotalRating(recipeRepository.countRatingRecipesByRecipeId(recipeId));
        return response;
    }
    public String deleteRecipe(int recipeId) {

        recipeRepository.deleteById(recipeId);
        return "Recipe with id" + recipeId + " deleted";
    }
    public ObjectSetResponse<UserSlimResponseDto> getListUser(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<UserSlimResponseDto> pages = userRepository.findAllUserSlim(pageable);
        Set<UserSlimResponseDto> users = new HashSet<>(pages.getContent());
        ObjectSetResponse<UserSlimResponseDto> response = new ObjectSetResponse<>();
        response.setContent(users);
        response.setTotalPages(pages.getTotalPages());
        response.setTotalElements(pages.getTotalElements());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());
        return response;
    }
}
