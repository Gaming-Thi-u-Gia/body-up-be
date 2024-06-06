package com.bodyupbe.bodyupbe.model.user;


import com.bodyupbe.bodyupbe.model.community.Badge;
import com.bodyupbe.bodyupbe.repository.BadgeRepository;
import com.bodyupbe.bodyupbe.repository.UserRepository;

public class DemoUser {
    UserRepository userRepository;
    static BadgeRepository badgeRepository;
    public static void demo() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("abc@gmail.com")
                .password("password")
                .build();
//        userRepository.save(user);

    }
}
