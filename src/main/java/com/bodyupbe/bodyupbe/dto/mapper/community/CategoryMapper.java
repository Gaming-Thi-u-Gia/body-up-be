package com.bodyupbe.bodyupbe.dto.mapper.community;

import com.bodyupbe.bodyupbe.dto.request.community.CategoryCommunityDto;
import com.bodyupbe.bodyupbe.model.community.CategoryCommunity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryCommunityDto toCategoryCommunityDto(CategoryCommunity categoryCommunity);

    CategoryCommunity toCategoryCommunity(CategoryCommunityDto categoryCommunityDto);
}
