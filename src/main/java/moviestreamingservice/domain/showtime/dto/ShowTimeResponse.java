package moviestreamingservice.domain.showtime.dto;

import moviestreamingservice.domain.movie.dto.MovieResponse;

import java.time.LocalDateTime;

public record ShowTimeResponse(
        Long id,
        double pricePerSeat,
        LocalDateTime startTime,
        int availableSeats,
        Long hallId,
        MovieResponse movieResponse
) {
}
