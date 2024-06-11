package com.bodyupbe.bodyupbe.service.user;

import com.bodyupbe.bodyupbe.dto.mapper.user.UserMapper;
import com.bodyupbe.bodyupbe.dto.request.user.UserChallengeRequestDto;
import com.bodyupbe.bodyupbe.dto.response.user.UserChallengeResponseDto;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.user.UserChallenge;
import com.bodyupbe.bodyupbe.model.workout_program.WorkoutProgram;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.repository.WorkoutProgramRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserChallengeService {
    UserRepository userRepository;
    UserMapper userMapper;
    private final WorkoutProgramRepository workoutProgramRepository;

    public Set<UserChallengeResponseDto> getAllUserChallenges(User user) {
        return userMapper.toListUserChallengeResponseDto(user.getUserChallenges());
    }

    public UserChallengeResponseDto addUserChallenge(User user, int workoutProgramId) {
        for (UserChallenge userChallenge : user.getUserChallenges()) {
            if (userChallenge.getStatus().equals("uncompleted")) {
                throw new RuntimeException("You have an uncompleted challenge");
            }
        }
        WorkoutProgram workoutProgram = workoutProgramRepository.findById(workoutProgramId).orElseThrow(() -> new RuntimeException("Workout program not found"));
        UserChallenge userChallenge = UserChallenge.builder()
                .user(user)
                .status("uncompleted")
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

    public UserChallengeResponseDto getUncompletedChallenge(User user) {
        return userMapper.toUserChallengeResponseDto(user.getUserChallenges().stream().filter(challenge -> challenge.getStatus().equals("uncompleted")).findFirst().orElseThrow(() -> new RuntimeException("Challenge not found")));
    }
}
