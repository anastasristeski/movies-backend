package moviestreamingservice.domain.showtime;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShowtimeRepository extends JpaRepository<ShowTime, Long> {
    List<ShowTime> findByHall_Cinema_Id(Long cinemaId);
    List<ShowTime> findByMovie_TmdbId(Long movieId);
    List<ShowTime> findByHall_Cinema_IdAndMovie_TmdbId(Long cinemaId, Long movieId);
    List<ShowTime> findByHallId(Long hallId);
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select s from ShowTime s where s.id =:id")
    Optional<ShowTime> findByIdForUpdate(@Param("id") Long id);
    @Query("""
  SELECT s FROM ShowTime s
  WHERE s.hall.cinema.id = :cinemaId
  AND DATE(s.startTime) = :date
""")
    List<ShowTime> findByCinemaAndDate(
            @Param("cinemaId") Long cinemaId,
            @Param("date") LocalDate date
    );

}
