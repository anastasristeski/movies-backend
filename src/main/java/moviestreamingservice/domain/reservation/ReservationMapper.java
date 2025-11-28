package moviestreamingservice.domain.reservation;

import moviestreamingservice.domain.reservation.dto.ReservationResponse;

public class ReservationMapper {
    public static ReservationResponse toResponse(Reservation reservation) {
        return new ReservationResponse(
                reservation.getId(),
                reservation.getUser().getEmail(),
                reservation.getShowTime().getId(),
                reservation.getSeats(),
                reservation.getStatus().name()
        );
    }
}
