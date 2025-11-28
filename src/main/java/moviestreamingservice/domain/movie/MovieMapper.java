package moviestreamingservice.domain.movie;

import moviestreamingservice.domain.movie.dto.MovieRequest;
import moviestreamingservice.domain.movie.dto.MovieResponse;

public class MovieMapper {
    public static MovieResponse toResponse(Movie movie) {
       return new MovieResponse( movie.getTmdbId(),
                movie.getTitle(),
                movie.getOverview(),
                movie.getPosterUrl(),
                movie.getBackdropUrl(),
                movie.getVoteAverage(),
                movie.getVoteCount(),
                movie.getPopularity(),
                movie.getReleaseDate(),
                movie.getGenres()
       );
    }
    public static Movie toEntity(MovieRequest request) {
        return Movie.builder()
                .tmdbId(request.tmdbId())
                .title(request.title())
                .overview(request.overview())
                .posterUrl(request.posterUrl())
                .backdropUrl(request.backdropUrl())
                .voteAverage(request.voteAverage())
                .voteCount(request.voteCount())
                .popularity(request.popularity())
                .releaseDate(request.releaseDate())
                .genres(request.genres())
                .build();
    }


}
