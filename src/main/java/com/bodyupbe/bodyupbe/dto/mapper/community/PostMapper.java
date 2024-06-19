package com.bodyupbe.bodyupbe.dto.mapper.community;

import com.bodyupbe.bodyupbe.dto.request.community.PostRequestDto;
import com.bodyupbe.bodyupbe.dto.response.community.PostCommentSlimDto;
import com.bodyupbe.bodyupbe.dto.response.community.PostResponseDto;
import com.bodyupbe.bodyupbe.dto.response.community.PostSlimResponse;
import com.bodyupbe.bodyupbe.model.community.Post;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PostMapper {
    PostRequestDto toPostDto(Post post);
    Post toPost(PostRequestDto postDto);
    PostResponseDto toPostResponseDto(Post post);
    PostSlimResponse toPostSlimResponse(Post post);

    List<PostResponseDto> toListPostResponseDto(List<Post> posts);

    Set<PostResponseDto> toSetPostResponseDto(Set<Post> posts);

    List<PostSlimResponse> toListPostSlimResponse(List<Post> posts);

    List<PostCommentSlimDto> toListPostCommentSlimDto(List<Post> posts);

    PostCommentSlimDto toPostCommentSlimDto(Post post);
}
