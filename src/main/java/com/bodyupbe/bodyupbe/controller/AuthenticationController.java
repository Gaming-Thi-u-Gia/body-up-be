package com.bodyupbe.bodyupbe.controller;

import com.bodyupbe.bodyupbe.service.AuthenticationService;
import com.bodyupbe.bodyupbe.model.User;
import com.bodyupbe.bodyupbe.model.UserGoogle;
import com.bodyupbe.bodyupbe.service.AuthenticationResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody User request, HttpSession session
    ) {
        return ResponseEntity.ok(authService.register(request, session));
    }
    @PostMapping("/logingoogle")
    public ResponseEntity<AuthenticationResponse> loginGoogle(@RequestBody UserGoogle user){
        return ResponseEntity.ok(authService.loginGoogle(user));

    }
    @PostMapping("/verify")
    public ResponseEntity<AuthenticationResponse> verifyCode(
             HttpSession session, @RequestParam String code
    ) {
        return ResponseEntity.ok(authService.verifyCode(session,code));
    }
    @PostMapping("/forgot-password")
    public ResponseEntity<AuthenticationResponse> forgotPassword(
            HttpSession session, @RequestParam String email
    ) {
        return ResponseEntity.ok(authService.sendPasswordResetCode(session, email));
    }

    @PostMapping("/resetPasscode")
    public ResponseEntity<AuthenticationResponse> verifyResetCode(
            HttpSession session, @RequestParam String code
    ) {
        return ResponseEntity.ok(authService.verifyPasswordResetCode(session, code));
    }
    @PutMapping("/reset-password")
    public ResponseEntity<AuthenticationResponse> resetPassword(
            @RequestBody User request, HttpSession session
    ) {
        return ResponseEntity.ok(authService.resetPassword(session, request));
    }



    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody User request
    ) {
        return ResponseEntity.ok(authService.login(request));
    }
}
