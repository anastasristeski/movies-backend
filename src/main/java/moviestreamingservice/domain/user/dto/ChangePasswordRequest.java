package moviestreamingservice.domain.user.dto;

public record ChangePasswordRequest(String oldPassword, String newPassword) {
}
