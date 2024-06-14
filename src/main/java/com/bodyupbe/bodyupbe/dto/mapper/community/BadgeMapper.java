package com.bodyupbe.bodyupbe.dto.mapper.community;

import com.bodyupbe.bodyupbe.dto.request.community.BadgeDto;
import com.bodyupbe.bodyupbe.model.community.Badge;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BadgeMapper {
    BadgeDto toBadgeDto(Badge badge);

    Badge toBadge(BadgeDto badgeDto);

}
