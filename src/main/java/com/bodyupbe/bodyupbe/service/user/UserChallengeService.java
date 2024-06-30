package com.bodyupbe.bodyupbe.service.user;

import com.bodyupbe.bodyupbe.dto.mapper.user.UserMapper;
import com.bodyupbe.bodyupbe.dto.mapper.workout.WorkoutMapper;
import com.bodyupbe.bodyupbe.dto.request.user.UserChallengeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserChallengeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserChallengeSlimResponseDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserDailyChallengeResponseDto;
import com.bodyupbe.bodyupbe.dto.response.workout_program.WorkoutProgramSlimResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.user.UserChallenge;
import com.bodyupbe.bodyupbe.model.user.UserDailyChallenge;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.bodyupbe.bodyupbe.repository.UserChallengeRepository;
import com.bodyupbe.bodyupbe.repository.UserDailyChallengeRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.repository.WorkoutProgramRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserChallengeService {
    UserRepository userRepository;
    UserMapper userMapper;
    private final WorkoutProgramRepository workoutProgramRepository;
    private final UserDailyChallengeRepository userDailyChallengeRepository;
    private final WorkoutMapper workoutMapper;
    private final UserChallengeRepository userChallengeRepository;

    public Set<UserChallengeSlimResponseDto> getAllUserChallenges(User user) {
        return userMapper.toListUserChallengeSlimResponseDto(user.getUserChallenges());
    }

    public UserChallengeResponseDto addUserChallenge(User user, int workoutProgramId) {
        for (UserChallenge userChallenge : user.getUserChallenges()) {
            if (userChallenge.getStatus().equals("uncomplete")) {
                throw new RuntimeException("You have an uncomplete challenge");
            }
        }
        WorkoutProgram workoutProgram = workoutProgramRepository.findById(workoutProgramId).orElseThrow(() -> new RuntimeException("Workout program not found"));
        UserChallenge userChallenge = UserChallenge.builder()
                .user(user)
                .status("uncomplete")
                .workoutProgram(workoutProgram)
                .build();
        user.getUserChallenges().add(userChallenge);
        return userMapper.toUserChallengeResponseDto(userChallenge);
    }

    public void deleteUserChallenge(User user, int challengeId) {
        UserChallenge userChallenge = user.getUserChallenges().stream().filter(challenge -> challenge.getId() == challengeId).findFirst().orElseThrow(() -> new RuntimeException("Challenge not found"));
        user.getUserChallenges().remove(userChallenge);
    }

    public UserChallengeResponseDto updateUserChallenge(User user, UserChallengeRequestDto userChallengeRequestDto) {
        UserChallenge userChallenge = user.getUserChallenges().stream().filter(challenge -> challenge.getId() == userChallengeRequestDto.getId()).findFirst().orElseThrow(() -> new RuntimeException("Challenge not found"));
        userChallenge.setStatus(userChallengeRequestDto.getStatus());
        return userMapper.toUserChallengeResponseDto(userChallenge);
    }

    public UserChallengeSlimResponseDto getUncompletedChallenge(User user) {
        return userMapper.toUserChallengeSlimResponseDto(user.getUserChallenges().stream()
                .filter(challenge -> "uncomplete".equals(challenge.getStatus()))
                .findFirst().orElseThrow(() -> new RuntimeException("Challenge not found")));
    }

    public Set<UserChallengeSlimResponseDto> getCompletedChallenge(User user) {
        return userMapper.toListUserChallengeSlimResponseDto(user.getUserChallenges().stream()
                .filter(challenge -> "complete".equals(challenge.getStatus()))
                .collect(Collectors.toSet()));
    }
    //findByUserIdAndDailyExerciseWorkoutProgramId
    public Set<UserDailyChallengeResponseDto> findByUserAndWorkoutProgram(Integer userId, Integer workoutProgramId) {
        Set<UserDailyChallenge> userChallenges = userDailyChallengeRepository.findByUserIdAndDailyExercise_WorkoutProgramId(userId, workoutProgramId);
        return userMapper.toListUserDailyChallengeResponseDto(userChallenges);
    }
    //get all workout programs
    public List<WorkoutProgramSlimResponseDto> getAllWorkoutPrograms(User user) {
        return workoutMapper.toListWorkoutProgramSlimResponseDto(workoutProgramRepository.findTop10WorkoutPrograms());
    }
    //get all days of a workout program
    public Set<UserDailyChallengeResponseDto> getAllDay(User user,Integer workoutProgramId) {
        return userMapper.toListUserDailyChallengeResponseDto(userDailyChallengeRepository.findAllByUserAndWorkoutProgram(user.getId(),workoutProgramId));
    }

    //get first uncompleted challenge
    public UserDailyChallengeResponseDto getFirstUncompletedChallenge(User user) {
        UserDailyChallenge userDailyChallenge = userDailyChallengeRepository.findByStatusSortedByExerciseDay("uncomplete", user.getId());
        if(userDailyChallenge == null){
            throw new RuntimeException("Doesn't have uncomplete challenge");
        }
        return userMapper.toUserDailyChallengeResponseDto(userDailyChallenge);
    }

    //mark user daily challenge as completed
    public void markChallengeAsCompleted(User user, int challengeId) {
        UserDailyChallenge userDailyChallenge = userDailyChallengeRepository.findByDailyExerciseIdAndUserId(challengeId,user.getId());
        userDailyChallenge.setStatus("complete");
        //update all video daily status
        userDailyChallenge.getDailyExercise().getDailyViveos().forEach(dailyVideo -> {
            dailyVideo.setStatus("complete");
        });
        userDailyChallengeRepository.save(userDailyChallenge);
    }
    //mark finish user challenge
    public void markFinishChallenge(User user, int challengeId) {
        UserChallenge userChallenge = userChallengeRepository.findByWorkoutProgramIdAndUserId(challengeId,user.getId());
        userChallenge.setStatus("complete");
        userChallengeRepository.save(userChallenge);
    }
}
