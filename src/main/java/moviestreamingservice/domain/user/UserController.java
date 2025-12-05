package moviestreamingservice.domain.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.user.dto.ChangePasswordRequest;
import moviestreamingservice.domain.user.dto.EditProfileRequest;
import moviestreamingservice.domain.user.dto.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/profile")
public class UserController {
    private final UserService userService;
    @GetMapping("/me")
    public UserResponse getCurrentUser(@AuthenticationPrincipal User user) {
        return new UserResponse(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName(), user.getCreatedAt());
    }
    @PutMapping("/edit")
    public ResponseEntity<UserResponse> editUser(@AuthenticationPrincipal User user, @RequestBody EditProfileRequest editProfileRequest) {
        return ResponseEntity.ok(userService.editProfile(user, editProfileRequest));
    }
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@AuthenticationPrincipal User user, @Valid @RequestBody ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(user, changePasswordRequest);
        return ResponseEntity.ok("Password changed successfully");
    }
}
