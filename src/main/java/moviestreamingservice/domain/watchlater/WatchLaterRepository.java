package moviestreamingservice.domain.watchlater;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
@Repository
public interface WatchLaterRepository extends JpaRepository<WatchLater, Long> {
    Optional<WatchLater> findByUserIdAndMovieTmdbId(Long userId, Long tmdbId);
    List<WatchLater> findAllByUserIdOrderByCreatedAtDesc(Long userId);

}
