package com.bodyupbe.bodyupbe.dto.request.community;

import com.bodyupbe.bodyupbe.dto.request.workout_program.FinishProgramTagDto;
import com.bodyupbe.bodyupbe.dto.request.user.UserDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.Set;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PostDto {
    Integer id;
    String title;
    String description;
    String imgBefore;
    String imgAfter;
    Date dayBefore;
    Date dayAfter;
    Date createdAt;
    UserDto userDto;
    CategoryCommunityDto categoryCommunityDto;
    BadgeDto badge;
    Set<CommentDto> commentDtos;
    Set<BookmarkPostDto> bookmarkPostDtos;
    Set<OtherImagePostDto> otherImagePostDtos;
    Set<FinishProgramTagDto> finishProgramTagDtos;
}
