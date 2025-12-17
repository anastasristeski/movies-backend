package moviestreamingservice.domain.reservation.dto;

import java.util.List;

public record SeatAvailabilityResponse(List<String> allSeats, List<String> takenSeats) {
}
