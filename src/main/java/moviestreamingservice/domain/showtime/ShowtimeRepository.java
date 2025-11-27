package moviestreamingservice.domain.showtime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShowtimeRepository extends JpaRepository<ShowTime, Long> {
    List<ShowTime> findByHall_Cinema_Id(Long cinemaId);
    List<ShowTime> findByMovie_TmdbId(Long movieId);
    List<ShowTime> findByHall_Cinema_IdAndMovie_TmdbId(Long cinemaId, Long movieId);

}
