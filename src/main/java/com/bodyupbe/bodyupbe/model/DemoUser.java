package com.bodyupbe.bodyupbe.model;

public class DemoUser {
    public void demo() {
        User user = User.builder()
                .firstName("John")
                .lastName("Doe")
                .email("abc@gmail.com")
                .password("password")
                .build();
    }
}
