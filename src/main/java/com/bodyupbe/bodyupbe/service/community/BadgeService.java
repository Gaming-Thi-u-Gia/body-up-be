package com.bodyupbe.bodyupbe.service.community;

import com.bodyupbe.bodyupbe.dto.mapper.community.BadgeMapper;
import com.bodyupbe.bodyupbe.dto.request.community.BadgeDto;
import com.bodyupbe.bodyupbe.model.community.Badge;
import com.bodyupbe.bodyupbe.repository.BadgeRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BadgeService {
    BadgeRepository badgeRepository;

    BadgeMapper badgeMapper;
    public List<BadgeDto> getBadgeAll() {
        List<Badge> badges = badgeRepository.findAll();
        List<BadgeDto> badgesDto = badgeMapper.toListBadgeDto(badges);
        return badgesDto;
    }


//getAllBagdeById
    public Badge getBadgeById(int badgeId) {
        return badgeRepository.findById(badgeId).orElseThrow(() -> new RuntimeException("Badge not found"));
    }


}
