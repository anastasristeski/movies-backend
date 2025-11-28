package moviestreamingservice.domain.reservation.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record ReservationRequest(@NotEmpty List<String> seats) {
}
