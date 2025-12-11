package moviestreamingservice.domain.cinema;

import moviestreamingservice.domain.cinema.dto.CinemaResponse;
import moviestreamingservice.domain.movie.Movie;
import moviestreamingservice.domain.movie.dto.MovieResponse;

public class CinemaMapper {
    public static CinemaResponse toResponse(Cinema cinema) {
        return new CinemaResponse(
                cinema.getId(),
                cinema.getName(),
                cinema.getLocation(),
                cinema.getCity().getId()
        );
    }
}
