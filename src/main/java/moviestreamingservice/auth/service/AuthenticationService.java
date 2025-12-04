package moviestreamingservice.auth.service;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import moviestreamingservice.auth.dto.AuthenticationRequest;
import moviestreamingservice.auth.dto.AuthenticationResponse;
import moviestreamingservice.auth.dto.RegisterRequest;
import moviestreamingservice.config.JwtService;
import moviestreamingservice.domain.user.Role;
import moviestreamingservice.domain.user.User;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.user.UserRepository;
import moviestreamingservice.exception.BadRequestException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    public ResponseEntity<?> register(RegisterRequest request) {
       var user = User.builder()
               .firstName(request.getFirstName())
               .lastName(request.getLastName())
               .email(request.getEmail())
               .password(passwordEncoder.encode(request.getPassword()))
               .role(Role.USER)
               .build();
       repository.save(user);
       return ResponseEntity.ok("User registered successfully");
    }
    public ResponseEntity<AuthenticationResponse> authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate
                (new UsernamePasswordAuthenticationToken
                        (request.getEmail(), request.getPassword()));
        var user = repository
                .findByEmail(request.getEmail())
                .orElseThrow();
        var accessToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        ResponseCookie cookie = ResponseCookie.from("refresh_token", refreshToken)
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(7*24*60*60)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(AuthenticationResponse.builder()
                        .token(accessToken)
                        .build()
                );

    }
    public ResponseEntity<?> logout() {
        ResponseCookie cookie = ResponseCookie.from("refresh_token","")
                .httpOnly(true)
                .secure(true)
                .sameSite("Strict")
                .path("/")
                .maxAge(0)
                .build();
        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body("Logged out");
    }
    public ResponseEntity<AuthenticationResponse> refreshToken(HttpServletRequest request) {
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

}
