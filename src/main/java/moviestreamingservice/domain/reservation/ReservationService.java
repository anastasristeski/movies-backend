package moviestreamingservice.domain.reservation;

import lombok.RequiredArgsConstructor;
import moviestreamingservice.domain.showtime.ShowTime;
import moviestreamingservice.domain.showtime.ShowtimeRepository;
import moviestreamingservice.domain.user.User;
import moviestreamingservice.domain.user.UserRepository;
import moviestreamingservice.utilities.SeatUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ShowtimeRepository showTimeRepository;
    private final UserRepository userRepository;

    public Reservation createReservation(Long showTimeId, String email, List<String> seats) {

        ShowTime showTime = showTimeRepository.findById(showTimeId)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        List<String> allSeats = showTime.getHall().getSeats();

        for (String seat : seats) {

            if (!SeatUtil.seatExists(seat, allSeats)) {
                throw new RuntimeException("Invalid seat: " + seat);
            }

            boolean taken = reservationRepository
                    .existsByShowTime_IdAndSeatsContaining(showTimeId, seat);

            if (taken) {
                throw new RuntimeException("Seat already reserved: " + seat);
            }
        }

        Reservation reservation = Reservation.builder()
                .user(user)
                .showTime(showTime)
                .seats(seats)
                .status(ReservationStatus.UPCOMING)
                .build();

        return reservationRepository.save(reservation);
    }

    public List<Reservation> getUserReservations(String email) {
        return reservationRepository.findByUser_Email(email);
    }

    public void cancelReservation(Long reservationId, String email) {

        Reservation reservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        if (!reservation.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Not your reservation");
        }

        if (reservation.getStatus() != ReservationStatus.UPCOMING) {
            throw new RuntimeException("Only upcoming reservations can be cancelled");
        }

        reservation.setStatus(ReservationStatus.CANCELED);
        reservationRepository.save(reservation);
    }

    // AVAILABLE SEATS FOR UI
    public List<String> getAvailableSeats(Long showTimeId) {

        ShowTime showTime = showTimeRepository.findById(showTimeId)
                .orElseThrow(() -> new RuntimeException("Showtime not found"));

        List<String> allSeats = showTime.getHall().getSeats();
        List<String> reserved = reservationRepository.findSeatsByShowTimeId(showTimeId);

        return allSeats.stream()
                .filter(seat -> !reserved.contains(seat))
                .toList();
    }
}
