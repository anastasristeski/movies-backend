package moviestreamingservice.domain.watchlater;

import moviestreamingservice.domain.movie.dto.MovieResponse;

public record WatchLaterResponse(MovieResponse movieResponse, java.time.LocalDateTime createdAt) {

}
