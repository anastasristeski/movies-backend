package moviestreamingservice.domain.reservation;

import moviestreamingservice.domain.reservation.dto.ReservationResponse;
import moviestreamingservice.domain.showtime.ShowTime;

import java.util.List;

public class ReservationMapper {
    public static ReservationResponse toResponse(Reservation reservation) {
        ShowTime showTime = reservation.getShowTime();
        return new ReservationResponse(
                reservation.getId(),
                showTime.getMovie().getTitle(),
                showTime.getHall().getCinema().getName(),
                showTime.getStartTime().toString(),
                showTime.getHall().getHallNumber(),
                reservation.getSeats(),
                reservation.getStatus().name()

        );
    }
}
