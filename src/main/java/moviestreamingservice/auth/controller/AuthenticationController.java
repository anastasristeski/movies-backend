package moviestreamingservice.auth.controller;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import moviestreamingservice.auth.dto.AuthenticationRequest;
import moviestreamingservice.auth.dto.AuthenticationResponse;
import moviestreamingservice.auth.dto.RegisterRequest;
import moviestreamingservice.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.config.JwtService;
import moviestreamingservice.domain.user.User;
import moviestreamingservice.domain.user.UserRepository;
import moviestreamingservice.exception.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;
    private final JwtService jwtService;
    private final UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        return authenticationService.register(request);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return authenticationService.authenticate(request);
    }
    @PostMapping("/refresh")
    public ResponseEntity<AuthenticationResponse> refresh (HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) {
            throw new BadRequestException("Bad request for refresh token");
        }
        String refreshToken = Arrays.stream(cookies)
                .filter(cookie -> "refresh_token".equals(cookie.getName()))
                .findFirst()
                .map(Cookie::getValue)
                .orElse(null);
        if( refreshToken == null || jwtService.isTokenExpired(refreshToken)) {
            throw new BadRequestException("Refresh token expired");
        }
        String email = jwtService.extractUsername(refreshToken);
        User user = userRepository.findByEmail(email).orElseThrow(()-> new BadRequestException("Invalid email"));
        var newAccessToken = jwtService.generateToken(user);
        return ResponseEntity.ok(
                AuthenticationResponse.builder()
                        .token(newAccessToken)
                        .build()
        );
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return authenticationService.logout();
    }
}
