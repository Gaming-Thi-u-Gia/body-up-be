package com.bodyupbe.bodyupbe.dto.mapper;

import com.bodyupbe.bodyupbe.dto.response.NotificationResponseDto;
import com.bodyupbe.bodyupbe.model.Notification;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Set<NotificationResponseDto> toSetNotificationResponseDto(List<Notification> notifications);
}
