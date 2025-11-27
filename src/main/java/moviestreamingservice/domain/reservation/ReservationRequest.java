package moviestreamingservice.domain.reservation;

import java.util.List;

public record ReservationRequest(List<String> seats) {
}
