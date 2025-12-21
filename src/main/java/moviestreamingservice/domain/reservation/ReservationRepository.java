package moviestreamingservice.domain.reservation;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByShowTime_Id(Long showTimeId);
    List<Reservation> findByUser_Id(Long userId);
    boolean existsByShowTime_IdAndStatusAndSeatsContaining(Long showTimeId,ReservationStatus status ,String seat);
    List<Reservation> findByUser_Email(String email);
    boolean existsByShowTime_IdAndSeatsContaining(Long showTimeId, String seat);
    @Query("""
   SELECT seat
   FROM Reservation r
   JOIN r.seats seat
   WHERE r.showTime.id = :showTimeId
     AND r.status <> 'CANCELED'
""")
    List<String> findTakenSeatsByShowTimeId(@Param("showTimeId") Long showTimeId);
    @Query("SELECT COUNT(r) FROM Reservation r WHERE r.showTime.id = :showTimeId")
    int countReservationsForShowtime(@Param("showTimeId") Long showTimeId);
    @Query("""
    select distinct r from Reservation r
    join fetch r.seats s
    join fetch r.showTime st
    join fetch st.movie
    join fetch st.hall h
    join fetch h.cinema
    where r.user.id = :userId
""")
    List<Reservation> findAllByUserIdWithDetails(@Param("userId") Long userId);

}
