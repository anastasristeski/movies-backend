package moviestreamingservice.domain.showtime.dto;

import java.time.LocalDateTime;

public record ShowTimeResponse(
        Long id,
        double pricePerSeat,
        LocalDateTime startTime,
        int availableSeats,
        Long hallId,
        Long movieId
) {
}
