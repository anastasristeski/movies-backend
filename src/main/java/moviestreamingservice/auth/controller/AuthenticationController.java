package moviestreamingservice.auth.controller;


import jakarta.servlet.http.HttpServletRequest;
import moviestreamingservice.auth.dto.AuthenticationRequest;
import moviestreamingservice.auth.dto.AuthenticationResponse;
import moviestreamingservice.auth.dto.RegisterRequest;
import moviestreamingservice.auth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.config.JwtService;
import moviestreamingservice.domain.user.User;
import moviestreamingservice.domain.user.UserRepository;
import moviestreamingservice.domain.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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
     return authenticationService.refreshToken(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        return authenticationService.logout();
    }
}
