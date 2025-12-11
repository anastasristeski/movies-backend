package moviestreamingservice.domain.cinema.dto;

import jakarta.validation.constraints.NotBlank;

public record CinemaRequest(@NotBlank(message = "Cinema name is required")String name, @NotBlank(message = "Cinema location is required")String location) {
}
