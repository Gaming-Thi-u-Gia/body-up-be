package com.bodyupbe.bodyupbe.service;

import com.bodyupbe.bodyupbe.dto.mapper.NotificationMapper;
import com.bodyupbe.bodyupbe.dto.response.NotificationResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.RecipeCardResponseDto;
import com.bodyupbe.bodyupbe.dto.response.recipe.object_return.ObjectSetResponse;
import com.bodyupbe.bodyupbe.model.Notification;
import com.bodyupbe.bodyupbe.repository.NotificationRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NotificationService {
    NotificationRepository notificationRepository;
    UserRepository userRepository;
    NotificationMapper notificationMapper;
    @Scheduled(fixedRate = 300000)
    public void deleteOldNotifications() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, -1);
        Date cutoff = cal.getTime();

        log.info("Deleting notifications older than {}", cutoff);
        notificationRepository.deleteOldNotifications(cutoff);
    }
    public ObjectSetResponse<NotificationResponseDto> getNotifications(int userId, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<NotificationResponseDto> pages = userRepository.findAllNotificationById(pageable, userId);

        Set<NotificationResponseDto> content = pages.stream()
                .map(notificationResponseDto -> {
                    notificationResponseDto.setWorkoutProgram(notificationRepository.findWorkoutProgramNotificationById(notificationResponseDto.getId()));
                    return notificationResponseDto;
                })
                .collect(Collectors.toSet());
        ObjectSetResponse<NotificationResponseDto> response = new ObjectSetResponse<>();
        response.setContent(content);
        response.setTotalElements(pages.getTotalElements());
        response.setTotalPages(pages.getTotalPages());
        response.setPageNo(pages.getNumber());
        response.setPageSize(pages.getSize());
        response.setTotalPages(pages.getTotalPages());
        response.setLast(pages.isLast());
        return response;
    }
}
