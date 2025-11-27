package moviestreamingservice.domain.reservation;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation,Long> {
    List<Reservation> findByShowTime_Id(Long showTimeId);
    List<Reservation> findByUser_Id(Long userId);
    boolean existsByShowTime_IdAndStatusAndSeatsContaining(Long showTimeId,ReservationStatus status ,String seat);
    List<Reservation> findByUser_Email(String email);
    boolean existsByShowTime_IdAndSeatsContaining(Long showTimeId, String seat);
    List<String> findSeatsByShowTimeId(Long showTimeId);

}
