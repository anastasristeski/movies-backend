package moviestreamingservice.domain.showtime;

import moviestreamingservice.domain.showtime.dto.ShowTimeResponse;

public class ShowTimeMapper {
    public static ShowTimeResponse toResponse(ShowTime showTime) {
        int availableSeats = showTime.getHall().getTotalSeats() - showTime.getReservations().size();
        return new ShowTimeResponse(
                showTime.getId(),
                showTime.getPricePerSeat(),
                showTime.getStartTime(),
                availableSeats,
                showTime.getHall().getId(),
                showTime.getMovie().getTmdbId()
        );
    }
}
