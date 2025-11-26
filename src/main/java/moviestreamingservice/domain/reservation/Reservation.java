package moviestreamingservice.domain.reservation;

import moviestreamingservice.domain.showtime.ShowTime;
import moviestreamingservice.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="showtime_id")
    private ShowTime showTime;
    private int reservedSeats;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

}
