package moviestreamingservice.domain.city.dto;

import jakarta.validation.constraints.NotBlank;

public record CityRequest(@NotBlank(message="City name is required") String name) {
}
