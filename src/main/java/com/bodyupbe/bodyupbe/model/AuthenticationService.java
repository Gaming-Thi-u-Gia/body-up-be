package com.bodyupbe.bodyupbe.model;

import com.bodyupbe.bodyupbe.repository.UserRepository;
import com.bodyupbe.bodyupbe.service.AuthenticationResponse;
import com.bodyupbe.bodyupbe.service.JwtService;
import com.bodyupbe.bodyupbe.service.MailService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;
    private static final SecureRandom random = new SecureRandom();
    public static String generateVerificationCode() {
        int code = random.nextInt(999999);
        return String.format("%06d", code);
    }
    public AuthenticationResponse register(User request, HttpSession session) {
        Optional<User> existingUser = repository.findByEmail(request.getEmail());
        if (existingUser.isPresent()) {
            throw new Error("Email already exists");
        }
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        String verificationCode = generateVerificationCode();
        session.setAttribute("code", verificationCode);
        session.setAttribute("user", user);
        MailStructure mailStructure = new MailStructure();
        mailStructure.setSubject("Verification Code");
        mailStructure.setMessage("Your verification code is: " + verificationCode);
        mailService.sendMail(user.getEmail(), mailStructure);
        return new AuthenticationResponse("Verification email sent");
    }
    public AuthenticationResponse loginGoogle(UserGoogle request) {
        Optional<User> existingUser = repository.findByEmail(request.getEmail());
        User user = User.builder()
                .firstName(request.getGiven_name())
                .lastName(request.getFamily_name())
                .email(request.getEmail())
                .avatar(request.getPicture())
                .role(Role.USER)
                .build();
        if (existingUser.isPresent()) {
            String token = jwtService.generateToken(user);
            return new AuthenticationResponse(token);
        }
        repository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
    public AuthenticationResponse verifyCode(HttpSession session, String code) {
//        Optional<User> userOptional = repository.findByEmail(email);
        User user = (User) session.getAttribute("user");
        String verifyCode = (String) session.getAttribute("code");
        if(verifyCode.equals(code)) {
            repository.save(user);
            String token = jwtService.generateToken(user);
            return new AuthenticationResponse(token);
        }
        else {
            throw new Error("Invalid code");
        }
    }

    public AuthenticationResponse login(User request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
