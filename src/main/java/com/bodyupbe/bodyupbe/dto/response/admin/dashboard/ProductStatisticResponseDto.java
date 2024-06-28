package com.bodyupbe.bodyupbe.dto.response.admin.dashboard;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ProductStatisticResponseDto {
    int totalUser;
    int totalUserChallengeUncompleted;
    int totalUserChallengeCompleted;
    int totalWorkoutProgram;
    int totalVideo;
    int totalRecipe;
    int totalPost;
}
