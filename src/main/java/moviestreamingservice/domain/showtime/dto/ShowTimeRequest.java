package moviestreamingservice.domain.showtime.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record ShowTimeRequest(
        @Positive double pricePerSeat,
        @NotNull LocalDateTime startTime
        ) {}
