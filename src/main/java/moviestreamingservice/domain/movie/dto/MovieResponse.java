package moviestreamingservice.domain.movie.dto;

import java.util.List;

public record MovieResponse(
        Long tmdbId,
        String title,
        String overview,
        String posterUrl,
        String backdropUrl,
        double voteAverage,
        int voteCount,
        Double popularity,
        String releaseDate,
        List<String> genres
) {
}
