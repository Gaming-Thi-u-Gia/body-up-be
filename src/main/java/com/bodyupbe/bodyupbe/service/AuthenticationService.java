package com.bodyupbe.bodyupbe.service;

import com.bodyupbe.bodyupbe.dto.request.user.UserRequestDto;
import com.bodyupbe.bodyupbe.model.user.MailStructure;
import com.bodyupbe.bodyupbe.model.user.Role;
import com.bodyupbe.bodyupbe.model.user.User;
import com.bodyupbe.bodyupbe.model.user.UserGoogle;
import com.bodyupbe.bodyupbe.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//
import java.security.SecureRandom;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private static final Logger log = LoggerFactory.getLogger(AuthenticationService.class);
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
    public AuthenticationResponse register(UserRequestDto request, HttpSession session) {
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
                .userName("user")
                .userName2(request.getEmail().split("@")[0])
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
    public AuthenticationResponse sendPasswordResetCode( HttpSession session, String email) {
        Optional<User> userOptional = repository.findByEmail(email);
        System.out.println(email);
        if (userOptional.isEmpty()) {
            throw new Error("Email not found Hello");
        }
        User user = userOptional.get();
        String verificationCode = generateVerificationCode();
        session.setAttribute("resetCode", verificationCode);
        session.setAttribute("resetUser", user);
        MailStructure mailStructure = new MailStructure();
        mailStructure.setSubject("Password Reset Code");
        mailStructure.setMessage("Your password reset code is: " + verificationCode);
        mailService.sendMail(user.getEmail(), mailStructure);
        return new AuthenticationResponse("Password reset email sent");
    }

    public AuthenticationResponse verifyPasswordResetCode(HttpSession session, String code ) {
        User user = (User) session.getAttribute("resetUser");
        String resetCode = (String) session.getAttribute("resetCode");
        System.out.println(resetCode);
        if (resetCode.equals(code)) {
            session.setAttribute("passwordResetVerified", true);
            return new AuthenticationResponse("Code verified, you can now reset your password");
        } else {
            throw new Error("Invalid code");
        }
    }
    public AuthenticationResponse resetPassword(HttpSession session, UserRequestDto request ) {
        Boolean passwordResetVerified = (Boolean) session.getAttribute("passwordResetVerified");
        if (passwordResetVerified == null || !passwordResetVerified) {
            throw new Error("Password reset not verified");
        }
        User user = (User) session.getAttribute("resetUser");
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        repository.save(user);
        session.removeAttribute("passwordResetVerified");
        session.removeAttribute("resetUser");
        session.removeAttribute("resetCode");
        return new AuthenticationResponse("Password has been reset successfully");
    }


    public AuthenticationResponse login(UserRequestDto request) {
        authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        User user = repository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token);
    }
}
