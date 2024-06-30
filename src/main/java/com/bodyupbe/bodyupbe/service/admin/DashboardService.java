package com.bodyupbe.bodyupbe.service.admin;

import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.MonthlyUserCountResponseDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.ProductStatisticResponseDto;
import com.bodyupbe.bodyupbe.dto.response.admin.dashboard.UserChallengeStatusCountResponseDto;
import com.bodyupbe.bodyupbe.repository.*;
import jakarta.persistence.Tuple;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DashboardService {
    UserRepository userRepository;
    UserChallengeRepository userChallengeRepository;
    WorkoutProgramRepository workoutProgramRepository;
    VideoRepository videoRepository;
    RecipeRepository recipeRepository;
    PostRepository postRepository;

    public ProductStatisticResponseDto getTotalElement(){
        int countUser = userRepository.countUser();
        int userChallengeUncompleted = userChallengeRepository.countUserChallengeUncompleted();
        int userChallengeComplete = userChallengeRepository.countUserChallengeComplete();
        int countWorkoutProgram = workoutProgramRepository.countWorkoutProgram();
        int countVideo = videoRepository.countVideo();
        int countRecipe = recipeRepository.countRecipe();
        int countPost = postRepository.countPost();
        return new ProductStatisticResponseDto(countUser,userChallengeUncompleted,userChallengeComplete,countWorkoutProgram,countVideo,countRecipe,countPost);
    }

    public List<MonthlyUserCountResponseDto> getMonthlyUserCount() {
        Calendar calendar = new GregorianCalendar();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, -5);
        Date startDate = calendar.getTime();
        List<Object[]> results = userRepository.findUserCountByMonthSince(startDate);
        Map<String, Long> monthCountMap = results.stream()
                .collect(Collectors.toMap(
                        result -> (String) result[0],
                        result -> (Long) result[1]
                ));
        List<MonthlyUserCountResponseDto> response = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()).substring(0,3);
            String yearMonth = String.format("%d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
            Long count = monthCountMap.getOrDefault(yearMonth, 0L);
            response.add(new MonthlyUserCountResponseDto(monthName, count));
            calendar.add(Calendar.MONTH, 1);
        }
        return response;
    }
//    public Set<UserChallengeStatusCountResponseDto> getUserChallengeStatusCount() {
//        List<Tuple> results = userChallengeRepository.findUserChallengeStatusCount();
//        return results.stream()
//                .map(result -> new UserChallengeStatusCountResponseDto((String) result.get("status"), (Long) result.get("count")))
//                .collect(Collectors.toSet());
//    }
public List<MonthlyUserCountResponseDto> getMonthlyUserChallengeComletedCount() {
    Calendar calendar = new GregorianCalendar();
    calendar.set(Calendar.DAY_OF_MONTH, 1);
    calendar.add(Calendar.MONTH, -5);
    Date startDate = calendar.getTime();
    List<Object[]> results = userRepository.findUserCountByMonthSince(startDate);
    Map<String, Long> monthCountMap = results.stream()
            .collect(Collectors.toMap(
                    result -> (String) result[0],
                    result -> (Long) result[1]
            ));
    List<MonthlyUserCountResponseDto> response = new ArrayList<>();
    for (int i = 0; i < 6; i++) {
        String monthName = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault()).substring(0,3);
        String yearMonth = String.format("%d-%02d", calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1);
        Long count = monthCountMap.getOrDefault(yearMonth, 0L);
        response.add(new MonthlyUserCountResponseDto(monthName, count));
        calendar.add(Calendar.MONTH, 1);
    }
    return response;
}
}
