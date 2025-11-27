package moviestreamingservice.domain.showtime;

import moviestreamingservice.domain.hall.Hall;
import moviestreamingservice.domain.movie.Movie;
import moviestreamingservice.domain.reservation.Reservation;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="show_time")
public class ShowTime {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    @JoinColumn(name ="movie_id")
    private Movie movie;
    @ManyToOne
    @JoinColumn(name ="hall_id")
    private Hall hall;
    @OneToMany(mappedBy = "showTime", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();
    private double pricePerSeat;
    private int availableSeats;
    private LocalDateTime startTime;
}
