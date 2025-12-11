package moviestreamingservice.domain.hall;
import moviestreamingservice.domain.showtime.ShowTime;
import moviestreamingservice.domain.cinema.Cinema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="hall")
public class Hall {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int hallNumber;
    private int totalSeats;
    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;
    @OneToMany(mappedBy="hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ShowTime> showTimes = new ArrayList<>();
    @ElementCollection
    @CollectionTable(name = "hall_seats", joinColumns = @JoinColumn(name="hall_id"))
    @Column(name = "seat")
    private List<String> seats = new ArrayList<>();

}
