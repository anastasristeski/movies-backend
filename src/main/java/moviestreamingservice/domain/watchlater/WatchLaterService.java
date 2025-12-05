package moviestreamingservice.domain.watchlater;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.movie.Movie;
import moviestreamingservice.domain.movie.MovieMapper;
import moviestreamingservice.domain.movie.MovieRepository;
import moviestreamingservice.domain.user.User;
import moviestreamingservice.exception.NotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WatchLaterService {

    private final WatchLaterRepository watchLaterRepository;
    private final MovieRepository movieRepository;

    public WatchLaterResponse addToWatchLater(User user, Long tmdbId) {
        if (watchLaterRepository.findByUserIdAndMovieTmdbId(user.getId(), tmdbId).isPresent()) {
            return null;
        }

        Movie movie = movieRepository.findById(tmdbId)
                .orElseThrow(() -> new NotFoundException("Movie not found"));

        WatchLater wl = WatchLater.builder()
                .user(user)
                .movie(movie)
                .createdAt(LocalDateTime.now())
                .build();

        watchLaterRepository.save(wl);
        return new WatchLaterResponse(MovieMapper.toResponse(wl.getMovie()), wl.getCreatedAt());

    }

    public void removeFromWatchLater(User user, Long tmdbId) {
        WatchLater wl = watchLaterRepository.findByUserIdAndMovieTmdbId(user.getId(), tmdbId)
                .orElseThrow(() -> new NotFoundException("Item not in watch later"));

        watchLaterRepository.delete(wl);
    }

    public List<WatchLaterResponse> getWatchLater(User user) {
        return watchLaterRepository.findAllByUserIdOrderByCreatedAtDesc(user.getId())
                .stream()
                .map(wl -> new WatchLaterResponse(MovieMapper.toResponse(wl.getMovie()), wl.getCreatedAt()))
                .toList();
    }
}
