package moviestreamingservice.domain.reservation.dto;

import java.util.List;

public record ReservationResponse(
        Long id,
        String movieTitle,
        String cinemaName,
        String startTime,
        int hallNumber,
        List<String> seats,
        String status
) {}
