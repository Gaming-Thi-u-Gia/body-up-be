package com.bodyupbe.bodyupbe.dto.mapper.community;

import com.bodyupbe.bodyupbe.dto.mapper.user.UserMapper;
import com.bodyupbe.bodyupbe.dto.request.community.BookmarkPostDto;
import com.bodyupbe.bodyupbe.model.community.BookmarkPost;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface BookmarkPostMapper {
    BookmarkPostDto toBookmarkPostDto(BookmarkPost bookmarkPost);

    BookmarkPost toBookmarkPost(BookmarkPostDto bookmarkPostDto);
}
