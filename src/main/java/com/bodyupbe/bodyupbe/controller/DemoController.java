package com.bodyupbe.bodyupbe.controller;

import com.bodyupbe.bodyupbe.model.User;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class DemoController {
    private User user;
    @GetMapping("/api/v1/demo")
    public ResponseEntity<String> demo() {
        return ResponseEntity.ok("Hello World");
    }
}
