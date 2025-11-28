package moviestreamingservice.domain.hall.dto;

import jakarta.validation.constraints.Positive;

public record HallRequest(@Positive(message = "Hall number must be positive") int hallNumber, @Positive int totalSeats) {
}
