package moviestreamingservice.domain.trending;

import moviestreamingservice.domain.trending.dto.TrendingMovieResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrendingMovieService {

    private final TrendingMovieRepository repository;

    public List<TrendingMovieResponse> getTrendingToday() {
        return repository.findByType(TrendingType.DAY)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public List<TrendingMovieResponse> getTrendingWeek() {
        return repository.findByType(TrendingType.WEEK)
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    private TrendingMovieResponse mapToDto(TrendingMovie movie) {
        return TrendingMovieResponse.builder()
                .tmdbId(movie.getTmdbId())
                .title(movie.getTitle())
                .overview(movie.getOverview())
                .posterUrl(movie.getPosterUrl())
                .backdropUrl(movie.getBackdropUrl())
                .rating(movie.getRating())
                .popularity(movie.getPopularity())
                .releaseDate(movie.getReleaseDate())
                .type(movie.getType().name())
                .build();
    }
}