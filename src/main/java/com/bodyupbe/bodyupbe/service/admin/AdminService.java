package com.bodyupbe.bodyupbe.service.admin;

import com.bodyupbe.bodyupbe.dto.mapper.community.PostMapper;
import com.bodyupbe.bodyupbe.dto.mapper.recipe.RecipeMapper;
import com.bodyupbe.bodyupbe.dto.mapper.video.VideoMapper;
import com.bodyupbe.bodyupbe.dto.mapper.workout_program.WorkoutProgramMapper;
import com.bodyupbe.bodyupbe.dto.request.recipe.RecipeRequestDto;
import com.bodyupbe.bodyupbe.dto.request.workout_program.WorkoutProgramRequestDto;
import com.bodyupbe.bodyupbe.dto.request.workout_video.VideoRequestDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.*;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectSetResponse;
import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import com.bodyupbe.bodyupbe.model.Notification;
import com.bodyupbe.bodyupbe.model.Topic;
import com.bodyupbe.bodyupbe.model.community.Post;
import com.bodyupbe.bodyupbe.model.recipe.*;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgramCategory;
import com.bodyupbe.bodyupbe.model.workout_video.DailyExercise;
import com.bodyupbe.bodyupbe.model.workout_video.DailyVideo;
import com.bodyupbe.bodyupbe.model.workout_video.Video;
import com.bodyupbe.bodyupbe.model.workout_video.VideoCategory;
import com.bodyupbe.bodyupbe.repository.*;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    PostRepository postRepository;
    PostMapper postMapper;
    DailyRecipeRepository dailyRecipeRepository;
    DailyVideoRepository dailyVideoRepository;
    WorkoutProgramRepository workoutProgramRepository;
    WorkoutProgramCategoryRepository workoutProgramCategoryRepository;
    WorkoutProgramMapper workoutProgramMapper;
    DailyExerciseRepository dailyExerciseRepository;
    NotificationRepository notificationRepository;

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

    public ObjectSetResponse<RecipeCardResponseForAdminDto> getAllRecipeDetailForAdmin(int pageNo, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<RecipeCardResponseForAdminDto> pages = recipeRepository.findAllSlim(pageable, name);
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

    @Transactional
    public String updateRecipe(RecipeRequestDto request) {
        Recipe recipe = recipeRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        Set<RecipeCategory> categories = request.getRecipeCategories().stream()
                .map(categoryRequest -> recipeCategoryRepository.findById(categoryRequest.getId())
                        .orElseThrow(() -> new RuntimeException("RecipeCategory not found: " + categoryRequest.getId())))
                .collect(Collectors.toSet());
        recipe.setRecipeCategories(categories);

        Set<Topic> topics = request.getRecipeTopics().stream()
                .map(topicRequest -> topicRepository.findById(topicRequest.getId())
                        .orElseThrow(() -> new RuntimeException("Recipe Topic not found: " + topicRequest.getId())))
                .collect(Collectors.toSet());
        recipe.setRecipeTopics(topics);

        ingredientRecipeRepository.deleteByRecipeId(recipe.getId());
        noteRecipeRepository.deleteByRecipeId(recipe.getId());
        otherImageRecipeRepository.deleteByRecipeId(recipe.getId());

        Set<IngredientRecipe> ingredientRecipes = request.getIngredientRecipes().stream()
                .map(ingredientRequest -> {
                    IngredientRecipe ingredient = new IngredientRecipe();
                    ingredient.setAmount(ingredientRequest.getAmount());
                    ingredient.setName(ingredientRequest.getName());
                    ingredient.setRecipe(recipe);
                    return ingredient;
                })
                .collect(Collectors.toSet());
        ingredientRecipeRepository.saveAll(ingredientRecipes);

        Set<OtherImageRecipe> otherImageRecipes = request.getOtherImageRecipes().stream()
                .map(otherImageRequest -> {
                    OtherImageRecipe otherImageRecipe = new OtherImageRecipe();
                    otherImageRecipe.setImg(otherImageRequest.getImg());
                    otherImageRecipe.setRecipe(recipe);
                    return otherImageRecipe;
                })
                .collect(Collectors.toSet());
        otherImageRecipeRepository.saveAll(otherImageRecipes);

        Set<NoteRecipe> noteRecipes = request.getNoteRecipes().stream()
                .map(noteRecipeRequest -> {
                    NoteRecipe noteRecipe = new NoteRecipe();
                    noteRecipe.setDetail(noteRecipeRequest.getDetail());
                    noteRecipe.setRecipe(recipe);
                    return noteRecipe;
                })
                .collect(Collectors.toSet());
        noteRecipeRepository.saveAll(noteRecipes);

        recipe.setIngredientRecipes(ingredientRecipes);
        recipe.setOtherImageRecipes(otherImageRecipes);
        recipe.setNoteRecipes(noteRecipes);
        recipe.setName(request.getName());
        recipe.setImg(request.getImg());
        recipe.setPrepTime(request.getPrepTime());
        recipe.setCookTime(request.getCookTime());
        recipe.setCookingInstruction(request.getCookingInstruction());
        recipe.setDetail(request.getDetail());

        recipeRepository.save(recipe);

        return "Update Recipe Successfully With Recipe ID: " + recipe.getId();
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

    public ObjectSetResponse<UserSlimResponseDto> getListUser(int pageNo, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<UserSlimResponseDto> pages = userRepository.findAllUserSlim(pageable, name);
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

    public ObjectSetResponse<VideoCardResponseForAdminDto> getListVideo(int pageNo, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<VideoCardResponseForAdminDto> pages = videoRepository.getListVideoForAdmin(pageable, name);
        ObjectSetResponse<VideoCardResponseForAdminDto> response = new ObjectSetResponse<>();
        response.setContent(new HashSet<>(pages.getContent()));
        response.setTotalPages(pages.getTotalPages());
        response.setTotalElements(pages.getTotalElements());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());
        return response;
    }

    @Transactional
    public String updateVideo(VideoRequestDto request) {
        Video video = videoRepository.findById(request.getId()).orElseThrow(() ->
                new RuntimeException("Video not found"));
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
        video.setName(request.getName());
        video.setFeatured(request.isFeatured());
        video.setUrl(request.getUrl());
        videoRepository.save(video);
        return "Update Video Successfully With Video ID: " + video.getId();
    }

    public String deleteVideo(int videoId) {
        videoRepository.deleteById(videoId);
        return "Video with id" + videoId + " deleted";
    }

    public ObjectSetResponse<PostCardResponseForAdminDto> getListPost(int pageNo, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<PostCardResponseForAdminDto> pages = postRepository.findAllSlim(pageable, name);
        Set<PostCardResponseForAdminDto> content = postMapper.toPostCardResponseForAdminDto(pages.getContent());
        content.stream().map(post -> {
            post.setBadge(postRepository.findBageByPostId(post.getId()));
            return post;
        }).collect(Collectors.toSet());
        ObjectSetResponse<PostCardResponseForAdminDto> response = new ObjectSetResponse<>();
        response.setContent(content);
        response.setTotalPages(pages.getTotalPages());
        response.setTotalElements(pages.getTotalElements());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());
        return response;
    }

    public PostResponseForAdminDto getPostDetailForAdminById(int postId) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new RuntimeException("Post not found"));
        return postMapper.toPostResponseForAdminDto(post);
    }

    public String deletePost(int postId) {
        postRepository.deleteById(postId);
        return "Post with id" + postId + " deleted";
    }

    public VideoResponseForAdminDto getVideoDetailForAdminById(int videoId) {
        Video video = videoRepository.findById(videoId).orElseThrow(() ->
                new RuntimeException("Video not found"));
        return videoMapper.toVideoResponseForAdminDto(video);
    }

    public String addWorkoutProgram(WorkoutProgramRequestDto request) {
        WorkoutProgram workoutProgram = new WorkoutProgram();
        workoutProgram.setName(request.getName());
        workoutProgram.setType(request.getType());
        workoutProgram.setEquipment(request.getEquipment());
        workoutProgram.setDetail(request.getDetail());
        workoutProgram.setDay(request.getDay());
        workoutProgram.setTime(request.getTime());
        workoutProgram.setYear(request.getYear());
        workoutProgram.setImg(request.getImg());
        workoutProgram.setBanner(request.getBanner());

        Set<Topic> topics = request.getProgramTopics().stream()
                .map(topicRequest -> topicRepository.findById(topicRequest.getId())
                        .orElseThrow(() -> new RuntimeException("Topic not found: " + topicRequest.getId())))
                .collect(Collectors.toSet());
        workoutProgram.setProgramTopics(topics);

        Set<WorkoutProgramCategory> categories = request.getWorkoutProgramCategories().stream()
                .map(categoryRequest -> workoutProgramCategoryRepository.findById(categoryRequest.getId())
                        .orElseThrow(() -> new RuntimeException("Workout Program Category not found: " + categoryRequest.getId())))
                .collect(Collectors.toSet());
        workoutProgram.setWorkoutProgramCategories(categories);

        WorkoutProgram savedWorkoutProgram = workoutProgramRepository.save(workoutProgram);

        Set<DailyExercise> dailyExercises = request.getDailyExercises().stream()
                .map(dailyExerciseRequest -> {
                    DailyExercise dailyExercise = new DailyExercise();
                    dailyExercise.setDay(dailyExerciseRequest.getDay());
                    dailyExercise.setWorkoutProgram(savedWorkoutProgram);

                    DailyExercise savedDailyExercise = dailyExerciseRepository.save(dailyExercise);

                    Set<DailyVideo> dailyVideos = dailyExerciseRequest.getDailyVideos().stream()
                            .map(dailyVideoRequest -> {
                                DailyVideo dailyVideo = new DailyVideo();
                                dailyVideo.setDailyExercise(savedDailyExercise);

                                Video video = videoRepository.findById(dailyVideoRequest.getVideo().getId())
                                        .orElseThrow(() -> new RuntimeException("Video not found: " + dailyVideoRequest.getVideo().getId()));
                                dailyVideo.setVideo(video);
                                return dailyVideo;
                            })
                            .collect(Collectors.toSet());
                    savedDailyExercise.setDailyVideos(dailyVideos);

                    Set<DailyRecipe> dailyRecipes = dailyExerciseRequest.getDailyRecipes().stream()
                            .map(dailyRecipeRequest -> {
                                DailyRecipe dailyRecipe = new DailyRecipe();
                                dailyRecipe.setDailyExercise(savedDailyExercise);
                                dailyRecipe.setPart(dailyRecipeRequest.getPart());

                                Recipe recipe = recipeRepository.findById(dailyRecipeRequest.getRecipe().getId())
                                        .orElseThrow(() -> new RuntimeException("Recipe not found: " + dailyRecipeRequest.getRecipe().getId()));
                                dailyRecipe.setRecipe(recipe);
                                return dailyRecipe;
                            })
                            .collect(Collectors.toSet());
                    savedDailyExercise.setDailyRecipes(dailyRecipes);

                    dailyVideoRepository.saveAll(dailyVideos);
                    dailyRecipeRepository.saveAll(dailyRecipes);

                    return savedDailyExercise;
                })
                .collect(Collectors.toSet());

        savedWorkoutProgram.setDailyExercises(dailyExercises);
        workoutProgramRepository.save(savedWorkoutProgram);

        Notification notification = new Notification();
        notification.setMessage("There is a new workout program added: " + savedWorkoutProgram.getId());
        notification.setWorkoutProgram(savedWorkoutProgram);

        Notification savedNotification = notificationRepository.save(notification);

        savedWorkoutProgram.setNotification(savedNotification);
        workoutProgramRepository.save(savedWorkoutProgram);

        List<User> allUsers = userRepository.findAll();
        allUsers.forEach(user -> {
            user.getNotifications().add(savedNotification);
            savedNotification.getUsers().add(user);
            userRepository.save(user);
        });

        notificationRepository.save(savedNotification);

        return "Add New Workout Program Successfully With Program ID: " + savedWorkoutProgram.getId();
    }


    public List<VideoSelectForAdminResponseDto> getAllVideoSelectForAdmin() {
        return videoRepository.getVideoSelectForAdmin();
    }

    public List<RecipeSelectForAdminResponseDto> getAllRecipeSelectForAdmin() {
        return recipeRepository.getRecipeSelectForAdmin();
    }
    @Transactional
    public String deleteWorkoutProgram(int workoutProgramId) {
        workoutProgramRepository.deleteById(workoutProgramId);
        return "Workout Program with id" + workoutProgramId + " deleted";
    }

    public ObjectSetResponse<WorkoutProgramCardResponseForAdminDto> getListWorkoutProgram(int pageNo, int pageSize, String name) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("id").descending());
        Page<WorkoutProgramCardResponseForAdminDto> pages = workoutProgramRepository.findWorkoutProgramCardResponseForAdminDto(pageable, name);
        Set<WorkoutProgramCardResponseForAdminDto> content = new HashSet<>(pages.getContent());
        content.stream().map(workoutProgram -> {
            workoutProgram.setProgramTopics(new HashSet<>(topicRepository.findAllTopicsByWorkoutProgramId(workoutProgram.getId())));
            workoutProgram.setWorkoutProgramCategories(new HashSet<>(workoutProgramCategoryRepository.findAllWorkoutProgramCategoriesByWorkoutProgramId(workoutProgram.getId())));
            return workoutProgram;
        }).collect(Collectors.toSet());
        ObjectSetResponse<WorkoutProgramCardResponseForAdminDto> response = new ObjectSetResponse<>();
        response.setContent(content);
        response.setTotalPages(pages.getTotalPages());
        response.setTotalElements(pages.getTotalElements());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setLast(pages.isLast());
        return response;
    }

    public WorkoutProgramDetailForAdminDto getWorkoutProgramDetailForAdminById(int workoutProgramId) {
        WorkoutProgram workoutProgram = workoutProgramRepository.findById(workoutProgramId).orElseThrow(() ->
                new RuntimeException("Workout Program not found"));
        return workoutProgramMapper.toWorkoutProgramDetailForAdminDto(workoutProgram);
    }

    @Transactional
    public String updateWorkoutProgram(WorkoutProgramRequestDto request) {
        WorkoutProgram workoutProgram = workoutProgramRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Workout Program not found: " + request.getId()));

        dailyExerciseRepository.deleteByWorkoutProgramId(workoutProgram.getId());

        workoutProgram.setName(request.getName());
        workoutProgram.setType(request.getType());
        workoutProgram.setEquipment(request.getEquipment());
        workoutProgram.setDetail(request.getDetail());
        workoutProgram.setDay(request.getDay());
        workoutProgram.setTime(request.getTime());
        workoutProgram.setYear(request.getYear());
        workoutProgram.setImg(request.getImg());
        workoutProgram.setBanner(request.getBanner());

        Set<Topic> topics = request.getProgramTopics().stream()
                .map(topicRequest -> topicRepository.findById(topicRequest.getId())
                        .orElseThrow(() -> new RuntimeException("Topic not found: " + topicRequest.getId())))
                .collect(Collectors.toSet());
        workoutProgram.setProgramTopics(topics);

        Set<WorkoutProgramCategory> categories = request.getWorkoutProgramCategories().stream()
                .map(categoryRequest -> workoutProgramCategoryRepository.findById(categoryRequest.getId())
                        .orElseThrow(() -> new RuntimeException("Workout Program Category not found: " + categoryRequest.getId())))
                .collect(Collectors.toSet());
        workoutProgram.setWorkoutProgramCategories(categories);

        workoutProgramRepository.save(workoutProgram);

        dailyExerciseRepository.deleteByWorkoutProgramId(workoutProgram.getId());
        Set<DailyExercise> newDailyExercises = request.getDailyExercises().stream()
                .map(dailyExerciseRequest -> {
                    DailyExercise dailyExercise = new DailyExercise();
                    dailyExercise.setDay(dailyExerciseRequest.getDay());
                    dailyExercise.setWorkoutProgram(workoutProgram);

                    Set<DailyVideo> dailyVideos = dailyExerciseRequest.getDailyVideos().stream()
                            .map(dailyVideoRequest -> {
                                DailyVideo dailyVideo = new DailyVideo();
                                dailyVideo.setDailyExercise(dailyExercise);
                                Video video = videoRepository.findById(dailyVideoRequest.getVideo().getId())
                                        .orElseThrow(() -> new RuntimeException("Video not found: " + dailyVideoRequest.getVideo().getId()));
                                dailyVideo.setVideo(video);
                                return dailyVideo;
                            })
                            .collect(Collectors.toSet());
                    dailyExercise.setDailyVideos(dailyVideos);

                    Set<DailyRecipe> dailyRecipes = dailyExerciseRequest.getDailyRecipes().stream()
                            .map(dailyRecipeRequest -> {
                                DailyRecipe dailyRecipe = new DailyRecipe();
                                dailyRecipe.setDailyExercise(dailyExercise);
                                dailyRecipe.setPart(dailyRecipeRequest.getPart());
                                Recipe recipe = recipeRepository.findById(dailyRecipeRequest.getRecipe().getId())
                                        .orElseThrow(() -> new RuntimeException("Recipe not found: " + dailyRecipeRequest.getRecipe().getId()));
                                dailyRecipe.setRecipe(recipe);
                                return dailyRecipe;
                            })
                            .collect(Collectors.toSet());
                    dailyExercise.setDailyRecipes(dailyRecipes);

                    dailyVideoRepository.saveAll(dailyVideos);
                    dailyRecipeRepository.saveAll(dailyRecipes);

                    return dailyExercise;
                })
                .collect(Collectors.toSet());

        dailyExerciseRepository.saveAll(newDailyExercises);
        workoutProgram.setDailyExercises(newDailyExercises);
        workoutProgramRepository.save(workoutProgram);

        return "Update Workout Program Successfully With Program ID: " + workoutProgram.getId();
    }


    public List<TopUserChallengeResponseDto> getTop3UsersWithMostCompletedChallenges() {
        return userRepository.findTop3UsersWithMostCompletedChallenges();
    }

}