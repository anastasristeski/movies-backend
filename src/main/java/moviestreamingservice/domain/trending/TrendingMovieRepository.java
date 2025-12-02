package moviestreamingservice.domain.trending;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface TrendingMovieRepository extends JpaRepository<TrendingMovie, TrendingMovieId> {
    List<TrendingMovie> findByType(TrendingType type);
}
