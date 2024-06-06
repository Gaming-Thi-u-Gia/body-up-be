package com.bodyupbe.bodyupbe.service;

import com.bodyupbe.bodyupbe.model.community.Badge;
import com.bodyupbe.bodyupbe.repository.BadgeRepository;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InitializeDataImpl {
    BadgeRepository badgeRepository;
    @PostConstruct
    public void data() {
        //badge
        String [] badges = {
                "Workout",
                "Food",
                "Chloe's Programs",
                "Misc"
        };
        for (String badgeName : badges) {
            Badge badge = Badge.builder()
                    .name(badgeName)
                    .build();
            badgeRepository.save(badge);
        }

    }


}
