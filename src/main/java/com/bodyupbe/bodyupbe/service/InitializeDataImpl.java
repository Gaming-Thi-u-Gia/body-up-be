package com.bodyupbe.bodyupbe.service;

import com.bodyupbe.bodyupbe.model.community.Badge;
import com.bodyupbe.bodyupbe.model.community.CategoryCommunity;
import com.bodyupbe.bodyupbe.model.user.Role;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.repository.BadgeRepository;
import com.bodyupbe.bodyupbe.repository.CategoryCommunityRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InitializeDataImpl {
    BadgeRepository badgeRepository;
    CategoryCommunityRepository categoryCommunityRepository;
    UserRepository userRepository;

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
        String [] categoryCommunities = {
                "fitness",
                "before-after-results",
                "off-topic",
                "feedback",
                "help",
                "looking-for-team",
                "announcements"
        };
        for (String category : categoryCommunities) {
            CategoryCommunity categoryCommunity = CategoryCommunity.builder()
                    .name(category)
                    .build();
            categoryCommunityRepository.save(categoryCommunity);
        }


        User user = new User("user", "Johny", "Dang", "user", "$2a$10$/7xek5wpn9tYwwOAhhgUmONf3PHxnmidkRinXXVhXNX6IZiFOME9y", "avatar.png", "Software developer with 5 years of experience.", Role.USER, new Date());
        User admin = new User("admin", "Son", "Dang", "admin", "$2a$10$FU7/bVqNXkfHXcv7KxgImeos/MosQkgdQo5TWOWNafSkEmcp9Uh7G", "avatar.png", "Software developer with 5 years of experience.", Role.ADMIN, new Date());
        userRepository.save(user);
        userRepository.save(admin);
    }


}
