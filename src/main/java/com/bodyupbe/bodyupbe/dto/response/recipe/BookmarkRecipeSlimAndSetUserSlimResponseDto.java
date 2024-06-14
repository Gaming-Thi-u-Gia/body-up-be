package com.bodyupbe.bodyupbe.dto.response.recipe;

import com.bodyupbe.bodyupbe.dto.response.user.UserSlimResponseDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Set;
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookmarkRecipeSlimAndSetUserSlimResponseDto {
    Integer id;
    String name;
    double avgStar;
    int prepTime;
    int cookTime;
    String img;
    String cookDetail;
    Set<UserSlimResponseDto> bookmarkUsers;
}
