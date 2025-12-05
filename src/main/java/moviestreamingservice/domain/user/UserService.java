package moviestreamingservice.domain.user;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.user.dto.ChangePasswordRequest;
import moviestreamingservice.domain.user.dto.EditProfileRequest;
import moviestreamingservice.domain.user.dto.UserResponse;
import moviestreamingservice.exception.BadRequestException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public UserResponse editProfile(User user, EditProfileRequest editProfileRequest) {
        user.setFirstName(editProfileRequest.firstName());
        user.setLastName(editProfileRequest.lastName());
        user.setEmail(editProfileRequest.email());
        userRepository.save(user);
        return new UserResponse(
          user.getId(),
                user.getEmail(),
                user.getFirstName(),
                user.getLastName(),
                user.getCreatedAt()
        );
    }
    public void changePassword(User user, ChangePasswordRequest changePasswordRequest) {
        if(!passwordEncoder.matches(changePasswordRequest.oldPassword(), user.getPassword())){
            throw new BadRequestException("Old password doesn't match");
        }
        user.setPassword(passwordEncoder.encode(changePasswordRequest.newPassword()));
        userRepository.save(user);
    }
}
