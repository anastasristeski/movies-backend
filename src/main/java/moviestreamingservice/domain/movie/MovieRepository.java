package moviestreamingservice.domain.movie;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Page<Movie> findByTitleContainingIgnoreCase(String query, Pageable pageable);
    @Query("SELECT m.tmdbId FROM Movie m")
    List<Long> findAllTmdbIds();
    @Transactional
    @Modifying
    @Query("UPDATE Movie m SET m.runtime = :runtime WHERE m.tmdbId = :tmdbId")
    void updateRuntimeByTmdbId(Long tmdbId, Integer runtime);
}
