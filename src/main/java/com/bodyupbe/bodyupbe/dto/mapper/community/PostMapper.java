package com.bodyupbe.bodyupbe.dto.mapper.community;

import com.bodyupbe.bodyupbe.dto.request.community.PostDto;
import com.bodyupbe.bodyupbe.model.community.Post;
import org.mapstruct.Mapper;
@Mapper(componentModel = "spring")
public interface PostMapper {
    PostDto toPostDto(Post post);

    Post toPost(PostDto postDto);
}
