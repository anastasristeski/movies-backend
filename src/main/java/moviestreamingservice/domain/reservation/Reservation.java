package moviestreamingservice.domain.reservation;

import moviestreamingservice.domain.showtime.ShowTime;
import moviestreamingservice.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="showtime_id")
    private ShowTime showTime;

    @ElementCollection
    @CollectionTable(name="reservation_seats", joinColumns = @JoinColumn(name="reservation_id"))
    @Column(name="seat")
    private List<String> seats;
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
