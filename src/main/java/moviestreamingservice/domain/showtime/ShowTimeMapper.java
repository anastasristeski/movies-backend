package moviestreamingservice.domain.showtime;

import moviestreamingservice.domain.movie.MovieMapper;
import moviestreamingservice.domain.showtime.dto.ShowTimeResponse;

public class ShowTimeMapper {
    public static ShowTimeResponse toResponse(ShowTime showTime, int reservedCound) {
        int availableSeats = showTime.getHall().getTotalSeats() - reservedCound;
        return new ShowTimeResponse(
                showTime.getId(),
                showTime.getPricePerSeat(),
                showTime.getStartTime(),
                availableSeats,
                showTime.getHall().getId(),
                MovieMapper.toResponse(showTime.getMovie())
        );
    }
}
