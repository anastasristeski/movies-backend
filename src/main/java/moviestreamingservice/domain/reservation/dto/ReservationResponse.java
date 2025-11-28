package moviestreamingservice.domain.reservation.dto;

import java.util.List;

public record ReservationResponse(
        Long id,
        String userEmail,
        Long showTimeId,
        List<String> seats,
        String status
) {}
